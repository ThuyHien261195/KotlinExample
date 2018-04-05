package com.example.thuyhien.kotlinrxjava.data.interactor.impl

import android.util.Log
import com.example.thuyhien.kotlinrxjava.data.interactor.LoadDataInteractor
import com.example.thuyhien.kotlinrxjava.data.interactor.listener.LoadDataListener
import com.example.thuyhien.kotlinrxjava.data.network.retrofit.DataEndpointInterface
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.model.StoreCoupons
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by thuyhien on 3/22/18.
 */

class RetrofitLoadDataInteractor(val dataApiService: DataEndpointInterface) : LoadDataInteractor {
    override fun getTopCoupons(listener: LoadDataListener<List<Coupon>>) {
        val observable = dataApiService.getStoreCoupons("topcoupons")
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            listener.onLoadDataSucces(response.coupons)
                        },
                        { error ->
                            listener.onLoadDataFail(Exception(error))
                        },
                        {
                            -> Log.e("2359", "onComplete")
                        })
    }

    override fun getAllCouponsByStore(listener: LoadDataListener<List<Coupon>>) {
        Observable.just(this.dataApiService)
                .subscribeOn(Schedulers.computation())
                .flatMap({ s ->
                    s.getStoreInfo().subscribeOn(Schedulers.io())
                            .map(StoreCoupons::store)
                            .flatMap(s::getStoreCoupons)
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> listener.onLoadDataSucces(response.coupons) },
                        { error -> listener.onLoadDataFail(Exception(error)) })
    }

    override fun getMergeStoreCoupons(listener: LoadDataListener<StoreCoupons>) {
        Observable.just(this.dataApiService)
                .subscribeOn(Schedulers.computation())
                .flatMap { s ->
                    val couponObservable = s.getStoreCoupons("topcoupons")
                            .subscribeOn(Schedulers.io())
                    val storeCouponsInfoObservable = s.getStoreInfo()
                            .subscribeOn(Schedulers.io())
                    Observable.merge(couponObservable, storeCouponsInfoObservable)
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onLoadDataSucces,
                        { error -> listener.onLoadDataFail(Exception(error)) })
    }

    override fun getCalculatedCashBackStoreCoupons(listener: LoadDataListener<StoreCoupons>) {
        Observable.interval(1000L, TimeUnit.MILLISECONDS, Schedulers.io())
                .timeInterval()
                .flatMap { dataApiService.getStoreInfo() }
                .map { item ->
                    item.maxCashBack = "Max Cashback ${item.maxCashBack}"
                    item
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onLoadDataSucces,
                        { error -> listener.onLoadDataFail(Exception(error)) })
    }

    override fun getCouponFilterByDate(listener: LoadDataListener<Coupon>) {
        dataApiService.getStoreCoupons("topcoupons")
                .subscribeOn(Schedulers.computation())
                .flatMap { StoreCoupons -> Observable.fromIterable(StoreCoupons.coupons) }
                .filter { coupon -> !(coupon.expiryDate.equals("todayDt")) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onLoadDataSucces,
                        { error -> listener.onLoadDataFail(Exception(error)) })
    }


    override fun getCouponsWithRetry(listener: LoadDataListener<List<Coupon>>) {
        dataApiService.getStoreCoupons("topcoupons")
                .subscribeOn(Schedulers.io())
                .retry(4)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> listener.onLoadDataSucces(response.coupons) },
                        { error -> listener.onLoadDataFail(Exception(error)) })
    }
}