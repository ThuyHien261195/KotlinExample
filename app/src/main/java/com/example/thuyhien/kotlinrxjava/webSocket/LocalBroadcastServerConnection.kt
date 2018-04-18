package com.example.thuyhien.kotlinrxjava.webSocket

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by thuyhien on 4/18/18.
 */
class LocalBroadcastServerConnection(okHttpClient: OkHttpClient, val context: Context) : ServerConnection(okHttpClient) {
    override fun connect() {
        super.connect()
        val request = Request.Builder()
                .url(serverUrl)
                .build()
        webSocket = okHttpClient.newWebSocket(request, LocalBroadcastWebSocketListener(context))
    }
}