package com.example.thuyhien.kotlinrxjava.ui.exception

/**
 * Created by thuyhien on 4/23/18.
 */
data class InvalidInputException(val errorCode: String) : Exception() {

    constructor(errorCode: String, message: String) : this(errorCode)

    companion object {
        const val ERROR_EMPTY_USER = "EmptyUser"
        const val ERROR_EMPTY_PASS = "EmptyPassword"
    }
}