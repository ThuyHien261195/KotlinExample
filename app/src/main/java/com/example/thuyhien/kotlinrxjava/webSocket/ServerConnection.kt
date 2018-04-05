package com.example.thuyhien.kotlinrxjava.webSocket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

/**
 * Created by thuyhien on 3/30/18.
 */
class ServerConnection(var okHttpClient: OkHttpClient) {
    private val serverUrl = "wss://echo.websocket.org"
    private lateinit var webSocket: WebSocket

    fun connect() {
        val request = Request.Builder()
                .url(serverUrl)
                .build()
        webSocket = okHttpClient.newWebSocket(request, KotlinWebSocketListener())
    }

    fun disconnect() {
        webSocket.cancel()
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }
}