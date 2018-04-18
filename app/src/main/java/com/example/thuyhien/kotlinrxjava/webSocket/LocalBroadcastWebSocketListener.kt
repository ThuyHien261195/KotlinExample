package com.example.thuyhien.kotlinrxjava.webSocket

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
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
import java.lang.ref.WeakReference

/**
 * Created by thuyhien on 4/17/18.
 */
class LocalBroadcastWebSocketListener(context: Context) : WebSocketListener() {
    private val contextWeakRef = WeakReference<Context>(context)

    companion object {
        const val TAG_LOG_LOCAL_BROADCAST = "2359LocalBroadcast"
        const val ACTION_SHOW_COMMENT = "com.example.thuyhien.kotlinrxjava.ShowComment"
        const val ACTION_SHOW_CONNECTION_STATUS = "com.example.thuyhien.kotlinrxjava.ShowConnectionStatus"
        const val EXTRA_MESSAGE = "ExtraMessage"
        const val EXTRA_CONNECTION_STATUS = "ExtraConnectionStatus"
        const val NORMAL_CLOSURE_STATUS = 1000
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
        val intent = Intent()
        intent.action = ACTION_SHOW_COMMENT
        intent.putExtra(EXTRA_MESSAGE, mes)
        getContext()?.let {
            val localBroadcastManager = LocalBroadcastManager.getInstance(getContext()!!)
            localBroadcastManager.sendBroadcast(intent)
        }
    }

    private fun receiveConnectionStatus(status: Any) {
        val statusString = if (status == ConnectionStatus.CONNECTED) {
            "Connected websocket"
        } else {
            "Disconnected websocket"
        }
        val intent = Intent()
        intent.action = ACTION_SHOW_CONNECTION_STATUS
        intent.putExtra(EXTRA_CONNECTION_STATUS, statusString)
        getContext()?.let {
            val localBroadcastManager = LocalBroadcastManager.getInstance(getContext()!!)
            localBroadcastManager.sendBroadcast(intent)
        }
    }

    private fun getContext() = contextWeakRef.get()
}
