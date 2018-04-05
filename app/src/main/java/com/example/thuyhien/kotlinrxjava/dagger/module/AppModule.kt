package com.example.thuyhien.kotlinrxjava.dagger.module

import android.content.Context
import com.example.thuyhien.kotlinrxjava.KotlinApplication
import com.example.thuyhien.kotlinrxjava.data.interactor.LoadDataInteractor
import com.example.thuyhien.kotlinrxjava.data.interactor.impl.RetrofitLoadDataInteractor
import com.example.thuyhien.kotlinrxjava.data.network.retrofit.DataEndpointInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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