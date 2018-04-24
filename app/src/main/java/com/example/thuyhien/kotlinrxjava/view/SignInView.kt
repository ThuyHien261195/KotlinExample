package com.example.thuyhien.kotlinrxjava.view

/**
 * Created by thuyhien on 4/23/18.
 */
interface SignInView {
    fun signInSuccessfully(uriString: String)

    fun showError(errorCode: String)
}