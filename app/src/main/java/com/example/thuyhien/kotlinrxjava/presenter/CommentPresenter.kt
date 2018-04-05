package com.example.thuyhien.kotlinrxjava.presenter

import com.example.thuyhien.kotlinrxjava.model.Comment

/**
 * Created by thuyhien on 3/29/18.
 */
interface CommentPresenter {
    fun connectServer()

    fun disconnectServer()

    fun sendMessageToServer(message: String)
}