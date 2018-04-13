package com.example.thuyhien.kotlinrxjava.dagger.module

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.ui.activity.CommentActivity
import com.example.thuyhien.kotlinrxjava.ui.activity.CouponActivity
import com.example.thuyhien.kotlinrxjava.ui.activity.ExampleServiceActivity
import com.example.thuyhien.kotlinrxjava.ui.activity.MainActivity
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
}