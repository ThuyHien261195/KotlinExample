package com.example.thuyhien.kotlinrxjava.data.interactor

import com.example.thuyhien.kotlinrxjava.data.interactor.listener.LoadDataListener
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.model.StoreCoupons

/**
 * Created by thuyhien on 3/19/18.
 */
interface LoadDataInteractor {
    fun getTopCoupons(listener: LoadDataListener<List<Coupon>>)

    fun getAllCouponsByStore(listener: LoadDataListener<List<Coupon>>)

    fun getMergeStoreCoupons(listener: LoadDataListener<StoreCoupons>)

    fun getCalculatedCashBackStoreCoupons(listener: LoadDataListener<StoreCoupons>)

    fun getCouponFilterByDate(listener: LoadDataListener<Coupon>)

    fun getCouponsWithRetry(listener: LoadDataListener<List<Coupon>>)
}