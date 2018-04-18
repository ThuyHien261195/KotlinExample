package com.example.thuyhien.kotlinrxjava.dagger.module.common

import android.content.Context
import com.example.thuyhien.kotlinrxjava.webSocket.LocalBroadcastServerConnection
import com.example.thuyhien.kotlinrxjava.webSocket.ServerConnection
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by thuyhien on 3/28/18.
 */
@Module
abstract class WebSocketModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .readTimeout(3, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun provideServerConnection(okHttpClient: OkHttpClient): ServerConnection {
            return ServerConnection(okHttpClient)
        }

        @Provides
        @JvmStatic
        @Singleton
        fun provideLocalBroadcastServerConnection(okHttpClient: OkHttpClient, context: Context): LocalBroadcastServerConnection {
            return LocalBroadcastServerConnection(okHttpClient, context)
        }
    }
}