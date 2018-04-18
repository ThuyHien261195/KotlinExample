package com.example.thuyhien.kotlinrxjava.dagger.module.acitivity

import com.example.thuyhien.kotlinrxjava.presenter.CommentPresenter
import com.example.thuyhien.kotlinrxjava.presenter.impl.CommentPreseterImpl
import dagger.Binds
import dagger.Module

/**
 * Created by thuyhien on 3/29/18.
 */
@Module
abstract class CommentModule {

    @Binds
    abstract fun provideCommentPresenter(commentPresenterImpl: CommentPreseterImpl): CommentPresenter
}