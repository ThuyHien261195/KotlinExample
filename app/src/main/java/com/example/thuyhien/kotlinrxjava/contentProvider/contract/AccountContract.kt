package com.example.thuyhien.kotlinrxjava.contentProvider.contract

import android.net.Uri

/**
 * Created by thuyhien on 4/24/18.
 */
object AccountContract {
    const val CONTENT_AUTHORITY = "com.example.thuyhien.kotlinrxjava.contentProvider"
    val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")

    const val PATH_ACCOUNT = "account"
}