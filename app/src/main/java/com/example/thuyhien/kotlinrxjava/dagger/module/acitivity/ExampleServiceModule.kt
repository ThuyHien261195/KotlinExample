package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.ui.activity.ExampleServiceActivity
import com.example.thuyhien.kotlinrxjava.view.ExampleServiceView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 4/12/18.
 */
@Module
abstract class ExampleServiceModule {
    @ActivityScope
    @Binds
    abstract fun provideServiceActView(serviceActivity: ExampleServiceActivity): ExampleServiceView
}