package com.example.thuyhien.kotlinrxjava.dagger.module

import com.example.thuyhien.kotlinrxjava.dagger.scope.ActivityScope
import com.example.thuyhien.kotlinrxjava.presenter.CommentPresenter
import com.example.thuyhien.kotlinrxjava.presenter.impl.CommentPreseterImpl
import com.example.thuyhien.kotlinrxjava.ui.activity.CommentActivity
import com.example.thuyhien.kotlinrxjava.view.CommentView
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 3/29/18.
 */
@Module
abstract class CommentModule {
    @ActivityScope
    @Binds
    abstract fun provideCommentView(commentActivity: CommentActivity): CommentView

    @Binds
    abstract fun provideCommentPresenter(commentPresenterImpl: CommentPreseterImpl) : CommentPresenter
}