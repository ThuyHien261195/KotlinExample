package com.example.thuyhien.kotlinrxjava.presenter.impl

import android.content.Context
import android.util.Log
import com.example.thuyhien.kotlinrxjava.data.interactor.LoadDataInteractor
import com.example.thuyhien.kotlinrxjava.data.interactor.listener.LoadDataListener
import com.example.thuyhien.kotlinrxjava.extensions.hasInternetConnection
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.SQLiteInteractor
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.listener.SQLiteListener
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.presenter.CouponPresenter
import com.example.thuyhien.kotlinrxjava.ui.activity.CouponActivity
import com.example.thuyhien.kotlinrxjava.view.CouponView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by thuyhien on 3/21/18.
 */
class CouponPresenterImpl
@Inject constructor(couponView: CouponView,
                    private val loadDataInteractor: LoadDataInteractor,
                    private val sqliteInteractor: SQLiteInteractor,
                    private val context: Context)
    : CouponPresenter, LoadDataListener<List<Coupon>> {

    private val couponViewWeakRef: WeakReference<CouponView> = WeakReference(couponView)

    override fun getCouponList() {
        showLoading()

        if (context.hasInternetConnection()) {
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

    private fun showLoading() {
        getCouponView()?.showLoading()
    }

    private fun getCouponView() = couponViewWeakRef.get()
}