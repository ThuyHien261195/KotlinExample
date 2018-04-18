package com.example.thuyhien.kotlinrxjava.presenter

/**
 * Created by thuyhien on 4/17/18.
 */
interface LocalBroadcastPresenter {
    fun connectServer()

    fun disconnectServer()

    fun sendMessageToServer(message: String)
}