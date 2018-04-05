package com.example.thuyhien.kotlinrxjava.dagger.module

import com.example.thuyhien.kotlinrxjava.webSocket.KotlinWebSocketListener
import com.example.thuyhien.kotlinrxjava.webSocket.ServerConnection
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
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
        fun provideOkHttpClient() : OkHttpClient {
            return OkHttpClient.Builder()
                    .readTimeout(3, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun provideConnectionStatus(okHttpClient: OkHttpClient) : ServerConnection {
            return ServerConnection(okHttpClient)
        }
    }
}