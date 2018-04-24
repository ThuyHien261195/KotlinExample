package com.example.thuyhien.kotlinrxjava.localDatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry
import com.example.thuyhien.kotlinrxjava.localDatabase.table.DATABASE_NAME
import com.example.thuyhien.kotlinrxjava.localDatabase.table.DATABASE_VERSION

/**
 * Created by thuyhien on 4/23/18.
 */
class AccountDataHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_ACCOUNT = "CREATE TABLE ${AccountEntry.ACCOUNT_TABLE_NAME} (" +
            "${AccountEntry.ID} INTEGER PRIMARY KEY, " +
            "${AccountEntry.USER_COL} TEXT, " +
            "${AccountEntry.PASSWORD_COL} TEXT)"

    private val SQL_DELETE_ACCOUNT = "DROP TABLE IF EXISTS ${AccountEntry.ACCOUNT_TABLE_NAME}"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(SQL_CREATE_ACCOUNT)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(SQL_DELETE_ACCOUNT)
        onCreate(p0)
    }
}