package com.example.thuyhien.kotlinrxjava.contentProvider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.thuyhien.kotlinrxjava.contentProvider.contract.AccountContract.CONTENT_AUTHORITY
import com.example.thuyhien.kotlinrxjava.contentProvider.contract.AccountContract.PATH_ACCOUNT
import com.example.thuyhien.kotlinrxjava.localDatabase.AccountDataHelper
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.ACCOUNT_CONTENT_ITEM_TYPE
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.ACCOUNT_CONTENT_TYPE
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.ACCOUNT_TABLE_NAME
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.ID
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.USER_COL

/**
 * Created by thuyhien on 4/23/18.
 */
class AccountProvider : ContentProvider() {
    companion object {
        const val ACCOUNT = 1
        const val ACCOUNT_ID = 2

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(CONTENT_AUTHORITY, PATH_ACCOUNT, ACCOUNT)
            uriMatcher.addURI(CONTENT_AUTHORITY, "$PATH_ACCOUNT/#", ACCOUNT_ID)
        }
    }

    lateinit var sqliteDataBase: SQLiteDatabase

    override fun insert(p0: Uri?, p1: ContentValues?): Uri {
        val rowId = sqliteDataBase.insert(ACCOUNT_TABLE_NAME, null, p1)
        if (rowId > 0) {
            val uri = AccountEntry.buildAccountUri(rowId)
            context.contentResolver.notifyChange(uri, null)
            return uri
        }
        throw SQLException("Failed to add a record into $p0")
    }

    override fun query(p0: Uri?, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor {
        var cursor: Cursor

        when (uriMatcher.match(p0)) {
            ACCOUNT -> {
                cursor = sqliteDataBase.query(ACCOUNT_TABLE_NAME, p1, p2, p3, null, null, p4
                        ?: USER_COL)

            }
            ACCOUNT_ID -> {
                val id = ContentUris.parseId(p0)
                cursor = sqliteDataBase.query(ACCOUNT_TABLE_NAME, p1, "$ID = ?", arrayOf(id.toString()), null, null, p4
                        ?: USER_COL)
            }
            else -> {
                throw IllegalArgumentException("Unknown URI $p0")
            }
        }
        cursor.setNotificationUri(context.contentResolver, p0)
        return cursor
    }

    override fun onCreate(): Boolean {
        val context = getContext()
        val accountDataHelper = AccountDataHelper(context)
        sqliteDataBase = accountDataHelper.writableDatabase
        return sqliteDataBase != null
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        var count = 0;
        when (uriMatcher.match(p0)) {
            ACCOUNT -> count = sqliteDataBase.update(ACCOUNT_TABLE_NAME, p1, p2, p3)
            ACCOUNT_ID -> count = sqliteDataBase.update(ACCOUNT_TABLE_NAME, p1, "$ID = ${p0?.pathSegments?.get(1)} +" +
                    (p2?.isEmpty() ?: "AND ( + $p1)"), p3)
            else -> throw IllegalArgumentException("Unknown URI $p0")
        }
        context.contentResolver.notifyChange(p0, null)
        return count
    }

    override fun delete(p0: Uri?, p1: String?, p2: Array<out String>?): Int {
        var count = 0
        when (uriMatcher.match(p0)) {
            ACCOUNT -> count = sqliteDataBase.delete(ACCOUNT_TABLE_NAME, p1, p2)
            ACCOUNT_ID -> {
                val id = p0?.pathSegments?.get(1)
                count = sqliteDataBase.delete(ACCOUNT_TABLE_NAME, "$ID = $id" +
                        (p1?.isEmpty() ?: "AND ($p1)"), p2)
            }
            else -> throw IllegalArgumentException("Unknown URI $p0")
        }
        if (count != 0) {
            context.contentResolver.notifyChange(p0, null)
        }
        return count
    }

    override fun getType(p0: Uri?): String {
        when (uriMatcher.match(p0)) {
            ACCOUNT -> return ACCOUNT_CONTENT_TYPE
            ACCOUNT_ID -> return ACCOUNT_CONTENT_ITEM_TYPE
            else -> throw IllegalArgumentException("Unsupported URI: $p0")
        }
        return ""
    }
}