package com.example.thuyhien.kotlinrxjava.extension

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by thuyhien on 4/9/18.
 */
fun Context.hasInternetConnection() : Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
}