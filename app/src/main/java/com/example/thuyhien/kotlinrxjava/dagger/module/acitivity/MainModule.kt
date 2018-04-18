package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.ui.activity.MainActivity
import com.example.thuyhien.kotlinrxjava.view.MainView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 4/11/18.
 */
@Module
abstract class MainModule {
    @ActivityScope
    @Binds
    abstract fun provideMainView(mainActivity: MainActivity): MainView
}