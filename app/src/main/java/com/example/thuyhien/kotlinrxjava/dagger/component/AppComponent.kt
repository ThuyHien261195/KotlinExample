package com.example.thuyhien.kotlinrxjava.dagger.component

import android.content.Context
import com.example.thuyhien.kotlinrxjava.dagger.module.common.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Created by thuyhien on 3/21/18.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetModule::class,
        SQLiteModule::class,
        WebSocketModule::class,
        ActivityBuilderModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun bindsApplication(context: Context): Builder
    }
}