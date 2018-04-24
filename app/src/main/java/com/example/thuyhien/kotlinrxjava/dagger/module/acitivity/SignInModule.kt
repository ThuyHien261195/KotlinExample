package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.presenter.SignInPresenter
import com.example.thuyhien.kotlinrxjava.presenter.impl.SignInPresenterImpl
import com.example.thuyhien.kotlinrxjava.ui.activity.SignInActivity
import com.example.thuyhien.kotlinrxjava.view.SignInView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 4/24/18.
 */
@Module
abstract class SignInModule {
    @ActivityScope
    @Binds
    abstract fun provideSignInView(signInActivity: SignInActivity): SignInView

    @Binds
    abstract fun provideSignInPresenter(signInPresenterImpl: SignInPresenterImpl): SignInPresenter
}