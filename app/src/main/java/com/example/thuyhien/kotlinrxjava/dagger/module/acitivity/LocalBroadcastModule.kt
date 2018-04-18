package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.presenter.LocalBroadcastPresenter
import com.example.thuyhien.kotlinrxjava.presenter.impl.LocalBroadcastPresenterImpl
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 4/18/18.
 */
@Module
abstract class LocalBroadcastModule {
    @Binds
    abstract fun provideLocalBroadcastPresenter(localBroadcastPresenterImpl: LocalBroadcastPresenterImpl) : LocalBroadcastPresenter
}