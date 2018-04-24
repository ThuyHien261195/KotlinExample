package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.presenter.ContactPresenter
import com.example.thuyhien.kotlinrxjava.presenter.impl.ContactPresenterImpl
import com.example.thuyhien.kotlinrxjava.ui.activity.ContactActivity
import com.example.thuyhien.kotlinrxjava.view.ContactView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 4/20/18.
 */
@Module
abstract class ContactModule {
    @ActivityScope
    @Binds
    abstract fun provideContactView(contactActivity: ContactActivity): ContactView

    @Binds
    abstract fun provideContactPresenter(contactPresenterImpl: ContactPresenterImpl): ContactPresenter
}