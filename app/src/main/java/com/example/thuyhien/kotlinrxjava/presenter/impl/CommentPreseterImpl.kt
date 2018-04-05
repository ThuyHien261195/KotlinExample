package com.example.thuyhien.kotlinrxjava.presenter.impl

import com.example.thuyhien.kotlinrxjava.model.Comment
import com.example.thuyhien.kotlinrxjava.presenter.CommentPresenter
import com.example.thuyhien.kotlinrxjava.ui.activity.CommentActivity
import com.example.thuyhien.kotlinrxjava.view.CommentView
import com.example.thuyhien.kotlinrxjava.webSocket.KotlinWebSocketListener
import com.example.thuyhien.kotlinrxjava.webSocket.ServerConnection
import okhttp3.OkHttpClient
import okhttp3.WebSocket
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by thuyhien on 3/29/18.
 */
class CommentPreseterImpl
@Inject constructor(commentActivity: CommentActivity, private val serverConnection: ServerConnection) : CommentPresenter {

    private var commentWeakRef = WeakReference<CommentView>(commentActivity)

    override fun connectServer() {
        serverConnection.connect()
    }

    override fun disconnectServer() {
        serverConnection.disconnect()
    }

    override fun sendMessageToServer(message: String) {
        serverConnection.sendMessage(message)
    }

    private fun getCommentView() = commentWeakRef.get()
}