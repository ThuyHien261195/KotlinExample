package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.presenter.CountPresenter
import com.example.thuyhien.kotlinrxjava.presenter.impl.CountPresenterImpl
import com.example.thuyhien.kotlinrxjava.ui.activity.CountActivity
import com.example.thuyhien.kotlinrxjava.view.CountView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 4/16/18.
 */
@Module
abstract class CountModule {
    @ActivityScope
    @Binds
    abstract fun provideCountView(countActivity: CountActivity): CountView

    @Binds
    abstract fun provideCountPresenter(countPresenterImpl: CountPresenterImpl): CountPresenter
}