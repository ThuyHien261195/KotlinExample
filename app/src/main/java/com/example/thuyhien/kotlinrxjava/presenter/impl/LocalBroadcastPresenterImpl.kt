package com.example.thuyhien.kotlinrxjava.presenter.impl


import com.example.thuyhien.kotlinrxjava.presenter.LocalBroadcastPresenter
import com.example.thuyhien.kotlinrxjava.webSocket.LocalBroadcastServerConnection
import javax.inject.Inject

/**
 * Created by thuyhien on 4/17/18.
 */
class LocalBroadcastPresenterImpl
@Inject constructor(private val serverConnection: LocalBroadcastServerConnection) : LocalBroadcastPresenter {

    override fun connectServer() {
        serverConnection.connect()
    }

    override fun disconnectServer() {
        serverConnection.disconnect()
    }

    override fun sendMessageToServer(message: String) {
        serverConnection.sendMessage(message)
    }
}