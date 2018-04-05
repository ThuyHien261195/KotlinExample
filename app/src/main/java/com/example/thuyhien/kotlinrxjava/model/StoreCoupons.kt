package com.example.thuyhien.kotlinrxjava.model

/**
 * Created by thuyhien on 3/19/18.
 */
data class StoreCoupons (var store: String, var totalCoupons: String,
                    var maxCashBack: String, var coupons: List<Coupon>) {

}