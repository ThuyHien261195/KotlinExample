package com.example.thuyhien.kotlinrxjava.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.presenter.SignInPresenter
import com.example.thuyhien.kotlinrxjava.ui.exception.InvalidInputException
import com.example.thuyhien.kotlinrxjava.view.SignInView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject

class SignInActivity : DaggerAppCompatActivity(), SignInView {
    @Inject
    lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSignIn.setOnClickListener {
            signInPresenter.signIn(editTextUser.text.toString(), editTextPass.text.toString())
        }
    }

    override fun signInSuccessfully(uriString: String) {
        Toast.makeText(this, "Sign in: $uriString", Toast.LENGTH_LONG).show()
    }

    override fun showError(errorCode: String) {
        when (errorCode) {
            InvalidInputException.ERROR_EMPTY_USER -> {
                editTextUser.error = "User is not empty"
            }
            InvalidInputException.ERROR_EMPTY_PASS -> {
                editTextPass.error = "Password is not empty"
            }
        }
    }
}
