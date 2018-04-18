package com.example.thuyhien.kotlinrxjava.localDatabase.interactor.impl

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.thuyhien.kotlinrxjava.localDatabase.CouponDatabase
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.SQLiteInteractor
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.listener.SQLiteListener
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry.COUPON_CODE_COL
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry.COUPON_COL
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry.EXPIRY_DATE_COL
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry.ID_COL
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry.STORE_COL
import com.example.thuyhien.kotlinrxjava.localDatabase.table.CouponEntry.TABLE_NAME
import com.example.thuyhien.kotlinrxjava.model.Coupon

/**
 * Created by thuyhien on 3/26/18.
 */
class SQLiteInteractorImpl(val context: Context) : SQLiteInteractor {
    private val TAG = SQLiteInteractorImpl::class.java.simpleName

    private val dbHelper = CouponDatabase(context)

    override fun storeCouponList(couponList: List<Coupon>, sqliteListener: SQLiteListener<Boolean>) {
        for (coupon in couponList) {
            store(coupon, object : SQLiteListener<Long> {
                override fun loadDataSuccess(data: Long) {
                }

                override fun loadDataFail(error: Exception) {
                    sqliteListener.loadDataFail(error)
                }
            })
        }
        sqliteListener.loadDataSuccess(true)
    }

    override fun store(coupon: Coupon, sqliteListener: SQLiteListener<Long>) {
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        with(values) {
            put(STORE_COL, coupon.store)
            put(COUPON_COL, coupon.coupon)
            put(EXPIRY_DATE_COL, coupon.expiryDate)
            put(COUPON_CODE_COL, coupon.couponCode)
        }

        val id: Long = db.transaction {
            insert(CouponEntry.TABLE_NAME, null, values)
        }

        sqliteListener.loadDataSuccess(id)
    }

    override fun readAllCoupons(sqliteListener: SQLiteListener<List<Coupon>>) {
        val columns = arrayOf(ID_COL, STORE_COL, COUPON_COL, EXPIRY_DATE_COL, COUPON_CODE_COL)

        val order = "${ID_COL} ASC"

        val db = dbHelper.readableDatabase

        val cursor = db.doQuery(TABLE_NAME, columns, orderBy = order)

        val couponList = parseCouponFrom(cursor)

        sqliteListener.loadDataSuccess(couponList)
    }

    private fun parseCouponFrom(cursor: Cursor): MutableList<Coupon> {
        val couponList = mutableListOf<Coupon>()
        while (cursor.moveToNext()) {
            val store = cursor.getString(STORE_COL)
            val couponDetail = cursor.getString(COUPON_COL)
            val expiryDate = cursor.getString(EXPIRY_DATE_COL)
            val couponCode = cursor.getString(COUPON_CODE_COL)
            val coupon = Coupon(store, couponDetail, expiryDate, couponCode)
            couponList.add(coupon)
        }
        cursor.close()
        return couponList
    }
}

private fun SQLiteDatabase.doQuery(table: String, columns: Array<String>, selection: String? = null,
                                   selectionArgs: Array<String>? = null, groupBy: String? = null, having: String? = null,
                                   orderBy: String? = null): Cursor {
    return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
}

private fun Cursor.getString(columnName: String) = getString(getColumnIndex(columnName))

private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T {
    beginTransaction()
    val result = try {
        val returnValue = function()
        setTransactionSuccessful()
        returnValue
    } finally {
        endTransaction()
    }
    close()
    return result
}
