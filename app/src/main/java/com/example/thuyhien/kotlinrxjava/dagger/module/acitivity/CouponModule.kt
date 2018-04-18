package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.presenter.CouponPresenter
import com.example.thuyhien.kotlinrxjava.presenter.impl.CouponPresenterImpl
import com.example.thuyhien.kotlinrxjava.ui.activity.CouponActivity
import com.example.thuyhien.kotlinrxjava.view.CouponView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 3/22/18.
 */
@Module
abstract class CouponModule {
    @ActivityScope
    @Binds
    abstract fun provideCouponView(couponActivity: CouponActivity) : CouponView

    @Binds
    abstract fun provideCouponPresenter(couponPresenterImpl: CouponPresenterImpl) : CouponPresenter
}