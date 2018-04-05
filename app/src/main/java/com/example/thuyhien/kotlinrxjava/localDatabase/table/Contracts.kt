package com.example.thuyhien.kotlinrxjava.localDatabase.table

import android.provider.BaseColumns

/**
 * Created by thuyhien on 3/26/18.
 */

val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION = 10

object CouponEntry : BaseColumns {
    val TABLE_NAME = "habit"
    val ID_COL = "id"
    val STORE_COL = "store"
    val COUPON_COL = "coupon"
    val EXPIRY_DATE_COL = "expriyDate"
    val COUPON_CODE_COL = "couponCode"
}