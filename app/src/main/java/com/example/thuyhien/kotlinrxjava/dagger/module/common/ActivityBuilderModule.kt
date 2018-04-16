package com.example.thuyhien.kotlinrxjava.dagger.module.common

import com.example.thuyhien.kotlinrxjava.dagger.module.acitivity.*
import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.ui.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by thuyhien on 3/21/18.
 */
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindsMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CouponModule::class))
    abstract fun bindsCouponActivity(): CouponActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CommentModule::class))
    abstract fun bindsCommentActivity(): CommentActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(ExampleServiceModule::class))
    abstract fun bindsExampleServiceActivity(): ExampleServiceActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(BoundServiceModule::class))
    abstract fun bindsBoundServiceActivity(): BoundServiceActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CountModule::class))
    abstract fun bindsCountActivity(): CountActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(AlarmModule::class))
    abstract fun bindsAlarmActivity(): AlarmActivity
}