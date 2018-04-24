package com.example.thuyhien.kotlinrxjava.localDatabase.table

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns
import com.example.thuyhien.kotlinrxjava.contentProvider.contract.AccountContract

/**
 * Created by thuyhien on 4/23/18.
 */
class AccountEntry : BaseColumns {
    companion object {

        val ACCOUNT_CONTENT_URI = AccountContract.BASE_CONTENT_URI.buildUpon().appendPath(AccountContract.PATH_ACCOUNT).build()
        val ACCOUNT_CONTENT_TYPE = "com.example.thuyhien.dir/$ACCOUNT_CONTENT_URI/${AccountContract.PATH_ACCOUNT}"
        val ACCOUNT_CONTENT_ITEM_TYPE = "com.example.thuyhien.item/$ACCOUNT_CONTENT_URI/${AccountContract.PATH_ACCOUNT}"


        const val ACCOUNT_TABLE_NAME = "account"
        const val ID = "Id"
        const val USER_COL = "User"
        const val PASSWORD_COL = "Password"

        fun buildAccountUri(id: Long): Uri = ContentUris.withAppendedId(ACCOUNT_CONTENT_URI, id)
    }
}