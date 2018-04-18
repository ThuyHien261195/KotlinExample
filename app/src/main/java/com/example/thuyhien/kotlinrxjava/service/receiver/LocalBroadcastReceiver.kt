package com.example.thuyhien.kotlinrxjava.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.thuyhien.kotlinrxjava.model.Comment
import com.example.thuyhien.kotlinrxjava.ui.activity.LocalBroadcastActivity
import com.example.thuyhien.kotlinrxjava.ui.listener.LocalBroadcastActivityListener
import com.example.thuyhien.kotlinrxjava.webSocket.LocalBroadcastWebSocketListener
import com.example.thuyhien.kotlinrxjava.webSocket.LocalBroadcastWebSocketListener.Companion.TAG_LOG_LOCAL_BROADCAST
import java.lang.ref.WeakReference

/**
 * Created by thuyhien on 4/17/18.
 */
class LocalBroadcastReceiver(localBroadcastActivityListener: LocalBroadcastActivityListener) : BroadcastReceiver() {
    private val listenerWeakRef = WeakReference<LocalBroadcastActivityListener>(localBroadcastActivityListener)

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (intent.action) {
                LocalBroadcastWebSocketListener.ACTION_SHOW_COMMENT -> {
                    val comment = intent.getStringExtra(LocalBroadcastWebSocketListener.EXTRA_MESSAGE)
                    if (comment != null && !comment.isEmpty()) {
                        getLocalBroadcastActivityListener()?.showComment(Comment(comment = comment))
                    } else {
                        getLocalBroadcastActivityListener()?.showError("Error when receiving message.")
                    }
                }
                LocalBroadcastWebSocketListener.ACTION_SHOW_CONNECTION_STATUS -> {
                    val status = intent.getStringExtra(LocalBroadcastWebSocketListener.EXTRA_CONNECTION_STATUS)
                    if (status != null && !status.isEmpty()) {
                        getLocalBroadcastActivityListener()?.showConnectionStatus(status)
                    } else {
                        getLocalBroadcastActivityListener()?.showError("Error when receiving status")
                    }
                }
                else -> Log.e(TAG_LOG_LOCAL_BROADCAST, "OnReceive not has action")
            }
        }

        Log.e(TAG_LOG_LOCAL_BROADCAST, "OnReceive local broadcast")
    }

    private fun getLocalBroadcastActivityListener() = listenerWeakRef.get()
}