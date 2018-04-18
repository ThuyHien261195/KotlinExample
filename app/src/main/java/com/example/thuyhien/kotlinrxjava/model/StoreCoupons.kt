package com.example.thuyhien.kotlinrxjava.model

/**
 * Created by thuyhien on 3/19/18.
 */
data class StoreCoupons(val store: String, val totalCoupons: String,
                        var maxCashBack: String, val coupons: List<Coupon>) {

}