package com.example.thuyhien.kotlinrxjava.localDatabase.table

import android.provider.BaseColumns

/**
 * Created by thuyhien on 4/24/18.
 */
val DATABASE_NAME = "Kotlin.db"
val DATABASE_VERSION = 2

object CouponEntry : BaseColumns {
    val COUPON_TABLE_NAME = "habit"
    val ID_COL = "id"
    val STORE_COL = "store"
    val COUPON_COL = "coupon"
    val EXPIRY_DATE_COL = "expriyDate"
    val COUPON_CODE_COL = "couponCode"
}