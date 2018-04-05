package com.example.thuyhien.kotlinrxjava.presenter.impl

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.thuyhien.kotlinrxjava.data.interactor.LoadDataInteractor
import com.example.thuyhien.kotlinrxjava.data.interactor.listener.LoadDataListener
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.SQLiteInteractor
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.listener.SQLiteListener
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.presenter.CouponPresenter
import com.example.thuyhien.kotlinrxjava.ui.activity.MainActivity
import com.example.thuyhien.kotlinrxjava.view.CouponView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by thuyhien on 3/21/18.
 */
class CouponPresenterImpl
    @Inject constructor(mainActivity: MainActivity,
                        private var loadDataInteractor: LoadDataInteractor,
                        private var sqliteInteractor: SQLiteInteractor,
                        private var context: Context)
    : CouponPresenter, LoadDataListener<List<Coupon>> {

    private var couponViewWeakRef : WeakReference<CouponView> = WeakReference(mainActivity)

    override fun getCouponList() {
        showLoading()

        if (checkInternet()) {
            Log.e("2359 ", "has internet")
            loadDataInteractor.getTopCoupons(this)
        } else {
            Log.e("2359 ", "no internet")
            sqliteInteractor.readAllCoupons(object : SQLiteListener<List<Coupon>> {
                override fun loadDataSuccess(data: List<Coupon>) {
                    getCouponView()?.hideLoading()
                    getCouponView()?.displayCouponList(data)
                }

                override fun loadDataFail(error: Exception) {
                    getCouponView()?.showError(error.message!!)
                }
            })
        }
    }

    override fun onLoadDataSucces(data: List<Coupon>) {
        getCouponView()?.hideLoading()
        getCouponView()?.displayCouponList(data)
        sqliteInteractor.storeCouponList(data, object : SQLiteListener<Boolean> {
            override fun loadDataSuccess(data: Boolean) {
                Log.e("2359 load local data", "successfully")
            }

            override fun loadDataFail(error: Exception) {
                getCouponView()?.showError(error.message!!)
            }
        })
    }

    override fun onLoadDataFail(ex: Exception) {
        getCouponView()?.hideLoading()
        getCouponView()?.showError(ex.message!!)
    }

    private fun checkInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    private fun showLoading() {
        getCouponView()?.showLoading()
    }

    private fun getCouponView() = couponViewWeakRef.get()

    private fun exampleRetrofitGetData() {
        //        loadDataInteractor.getCouponFilterByDate(object: LoadDataListener<Coupon> {
//            override fun onLoadDataSucces(data: Coupon) {
//                Log.e("2359 Coupon by date: ", data.coupon)
//            }
//
//            override fun onLoadDataFail(ex: Exception) {
//                Log.e("2359 Error filter ", ex.message)
//            }
//        })

//        loadDataInteractor.getCalculatedCashBackStoreCoupons(object: LoadDataListener<StoreCoupons> {
//            override fun onLoadDataSucces(data: StoreCoupons) {
//                Log.e("2359 Max cash back: ", data.maxCashBack)
//            }
//
//            override fun onLoadDataFail(ex: Exception) {
//                Log.e("2359 Error map ", ex.message)
//            }
//        })

//        loadDataInteractor.getMergeStoreCoupons(object: LoadDataListener<StoreCoupons> {
//            override fun onLoadDataSucces(data: StoreCoupons) {
//                Log.e("2359 merge: ", data.store)
//            }
//
//            override fun onLoadDataFail(ex: Exception) {
//                Log.e("2359 Error merge ", ex.message)
//            }
//        })
//
//        loadDataInteractor.getAllCouponsByStore(object: LoadDataListener<List<Coupon>> {
//            override fun onLoadDataSucces(data: List<Coupon>) {
//                for(coupon in data) {
//                    Log.e("2359 all coupon: ", data.indexOf(coupon).toString() + " " + coupon.coupon)
//                }
//            }
//
//            override fun onLoadDataFail(ex: Exception) {
//                Log.e("2359 Error all coupon ", ex.message)
//            }
//        })
    }
}