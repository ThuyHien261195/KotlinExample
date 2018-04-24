package com.example.thuyhien.kotlinrxjava.presenter.impl

import android.content.ContentValues
import android.content.Context
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.ACCOUNT_CONTENT_URI
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.PASSWORD_COL
import com.example.thuyhien.kotlinrxjava.localDatabase.table.AccountEntry.Companion.USER_COL
import com.example.thuyhien.kotlinrxjava.presenter.SignInPresenter
import com.example.thuyhien.kotlinrxjava.ui.exception.InvalidInputException
import com.example.thuyhien.kotlinrxjava.view.SignInView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by thuyhien on 4/23/18.
 */
class SignInPresenterImpl
@Inject constructor(signInView: SignInView,
                    private val context: Context) : SignInPresenter {
    private val SignInViewWeakRef = WeakReference<SignInView>(signInView)

    override fun signIn(user: String, password: String) {
        var validUser = false
        var validPass = false

        try {
            validUser = validateUser(user)
        } catch (ex: InvalidInputException) {
            getSignInView()?.showError(ex.errorCode)
        }

        try {
            validPass = validatePass(password)
        } catch (ex: InvalidInputException) {
            getSignInView()?.showError(ex.errorCode)
        }

        if (validUser && validPass) {
            val contentValues = createContentValues(user, password)
            val uri = context.contentResolver.insert(ACCOUNT_CONTENT_URI, contentValues)
            getSignInView()?.signInSuccessfully(uri.toString())
        }
    }

    fun validateUser(user: String): Boolean {
        if (user.isEmpty()) {
            throw InvalidInputException(InvalidInputException.ERROR_EMPTY_USER)
        }
        return true
    }

    fun validatePass(pass: String): Boolean {
        if (pass.isEmpty()) {
            throw InvalidInputException(InvalidInputException.ERROR_EMPTY_PASS)
        }
        return true
    }

    private fun createContentValues(user: String, pass: String): ContentValues {
        val values = ContentValues()
        values.put(USER_COL, user)
        values.put(PASSWORD_COL, pass)
        return values
    }

    private fun getSignInView() = SignInViewWeakRef.get()
}