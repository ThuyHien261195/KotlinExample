package com.example.thuyhien.kotlinrxjava.presenter

/**
 * Created by thuyhien on 3/29/18.
 */
interface CommentPresenter {
    fun connectServer()

    fun disconnectServer()

    fun sendMessageToServer(message: String)
}