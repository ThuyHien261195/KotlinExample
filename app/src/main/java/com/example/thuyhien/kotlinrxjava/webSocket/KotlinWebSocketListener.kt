package com.example.thuyhien.kotlinrxjava.webSocket

import android.util.Log
import com.example.thuyhien.kotlinrxjava.EventBus.ConnectionEvent
import com.example.thuyhien.kotlinrxjava.EventBus.MessageEvent
import com.example.thuyhien.kotlinrxjava.model.Comment
import com.example.thuyhien.kotlinrxjava.model.enum.ConnectionStatus
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.greenrobot.eventbus.EventBus
import java.io.IOException

/**
 * Created by thuyhien on 3/28/18.
 */
class KotlinWebSocketListener : WebSocketListener() {
    private companion object {
        val NORMAL_CLOSURE_STATUS = 1000
    }

    override fun onOpen(webSocket: WebSocket?, response: Response?) {
        receiveConnectionStatus(ConnectionStatus.CONNECTED)
        Log.e("2359 onOpen", "connected")
    }

    override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
        Log.e("2359 onFailure", t?.message)
    }

    override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
        webSocket?.close(NORMAL_CLOSURE_STATUS, null)
        Log.e("2359 onClosing", code.toString() + "/ " + reason!!)
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        try {
            receiveMessage(text!!)
        } catch (e: Exception) {
            Log.e("2359 onMessage text", e.message)
        }
    }

    override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
        try {
            receiveMessage(bytes?.hex()!!)
        } catch (e: Exception) {
            Log.e("2359 onMessage bytes", e.message)
        }
    }

    override fun onClosed(webSocket: WebSocket?, code: Int, reason: String?) {
        receiveConnectionStatus(ConnectionStatus.DISCONNECTED)
        Log.e("2359 onClosed", "disconnected")
    }

    private fun receiveMessage(mes: String) {
        EventBus.getDefault().post(MessageEvent(Comment(comment = mes)))
    }

    private fun receiveConnectionStatus(status: Any) {
        var statusString = if (status == ConnectionStatus.CONNECTED) {
            "Connected websocket"
        } else {
            "Disconnected websocket"
        }
        EventBus.getDefault().post(ConnectionEvent(statusString))
    }
}