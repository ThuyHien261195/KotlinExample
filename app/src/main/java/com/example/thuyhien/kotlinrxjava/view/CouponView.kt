package com.example.thuyhien.kotlinrxjava.view

import com.example.thuyhien.kotlinrxjava.model.Coupon

/**
 * Created by thuyhien on 3/19/18.
 */
interface CouponView {
    fun displayCouponList(couponList: List<Coupon>)

    fun showError(error: String)

    fun showLoading()

    fun hideLoading()
}