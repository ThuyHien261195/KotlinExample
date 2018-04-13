package com.example.thuyhien.kotlinrxjava.dagger.module

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.ui.activity.BoundServiceActivity
import com.example.thuyhien.kotlinrxjava.view.BoundServiceView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 4/13/18.
 */
@Module
abstract class BoundServiceModule {
    @ActivityScope
    @Binds
    abstract fun provideBoundServiceView(boundServiceActivity: BoundServiceActivity) : BoundServiceView
}