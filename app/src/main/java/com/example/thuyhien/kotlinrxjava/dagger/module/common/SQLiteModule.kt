package com.example.thuyhien.kotlinrxjava.dagger.module.common

import android.content.Context
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.SQLiteInteractor
import com.example.thuyhien.kotlinrxjava.localDatabase.interactor.impl.SQLiteInteractorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by thuyhien on 3/26/18.
 */
@Module
abstract class SQLiteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSQLiteInteractor(context: Context) : SQLiteInteractor {
            return SQLiteInteractorImpl(context)
        }
    }
}