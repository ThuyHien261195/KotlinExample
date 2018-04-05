package com.example.thuyhien.kotlinrxjava.localDatabase.interactor

import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.listener.SQLiteListener
import com.example.thuyhien.kotlinrxjava.model.Coupon

/**
 * Created by thuyhien on 3/26/18.
 */
interface SQLiteInteractor {
    fun storeCouponList(couponList: List<Coupon>, sqliteListener: SQLiteListener<Boolean>)

    fun store(coupon: Coupon, sqliteListener: SQLiteListener<Long>)

    fun readAllCoupons(sqliteListener: SQLiteListener<List<Coupon>>)
}