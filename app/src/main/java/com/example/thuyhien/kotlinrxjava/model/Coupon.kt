package com.example.thuyhien.kotlinrxjava.model

/**
 * Created by thuyhien on 3/19/18.
 */
data class Coupon(var store: String, var coupon: String,
             var expiryDate: String, var couponCode: String) {
    constructor(store: String) : this(store, "", "", "") {
        println("constructor one parameter")
    }

    fun printCoupon() {
        println("Store " + store + " coupon " + coupon +
            " expiryDate " + expiryDate + " couponCode " + couponCode)
    }
}