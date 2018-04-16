package com.example.thuyhien.kotlinrxjava.dagger.module.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by thuyhien on 3/22/18.
 */
@Module
abstract class AppModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        fun provideDataGson(): Gson {
            val gsonBuilder = GsonBuilder()
            return gsonBuilder.create()
        }
    }
}