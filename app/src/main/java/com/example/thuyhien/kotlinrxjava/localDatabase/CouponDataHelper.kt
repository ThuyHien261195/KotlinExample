package com.example.thuyhien.kotlinrxjava.localDatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry
import com.example.thuyhien.kotlinrxjava.localDatabase.table.DATABASE_NAME
import com.example.thuyhien.kotlinrxjava.localDatabase.table.DATABASE_VERSION

/**
 * Created by thuyhien on 3/26/18.
 */
class CouponDataHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES = "CREATE TABLE ${CouponEntry.COUPON_TABLE_NAME} (" +
            "${CouponEntry.ID_COL} INTEGER PRIMARY KEY, " +
            "${CouponEntry.STORE_COL} TEXT, " +
            "${CouponEntry.COUPON_COL} TEXT, " +
            "${CouponEntry.EXPIRY_DATE_COL} TEXT, " +
            "${CouponEntry.COUPON_CODE_COL} TEXT" + ")"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${CouponEntry.COUPON_TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

}