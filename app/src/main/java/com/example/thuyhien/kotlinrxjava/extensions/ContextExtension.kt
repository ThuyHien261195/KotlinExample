package com.example.thuyhien.kotlinrxjava.extensions

import android.content.Context
import android.net.ConnectivityManager

fun Context.hasInternetConnection(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnected ?: false
}