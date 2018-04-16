package com.example.thuyhien.kotlinrxjava.dagger.module.common

import com.example.thuyhien.kotlinrxjava.data.interactor.LoadDataInteractor
import com.example.thuyhien.kotlinrxjava.data.interactor.impl.RetrofitLoadDataInteractor
import com.example.thuyhien.kotlinrxjava.data.network.retrofit.DataEndpointInterface
import com.google.gson.Gson
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
abstract class NetModule {
    @Module
    companion object {
        private val DATA_BASE_URL = "http://www.zoftino.com/api/";

        @Provides
        @JvmStatic
        @Singleton
        fun provideDataEndPointService(gson: Gson): DataEndpointInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(DATA_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(DataEndpointInterface::class.java)
        }

        @Provides
        @JvmStatic
        @Singleton
        fun provideLoadDataInteractor(dataApiService: DataEndpointInterface): LoadDataInteractor {
            return RetrofitLoadDataInteractor(dataApiService)
        }
    }
}