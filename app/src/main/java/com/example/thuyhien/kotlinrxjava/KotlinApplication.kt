package com.example.thuyhien.kotlinrxjava

import com.example.thuyhien.kotlinrxjava.dagger.component.AppComponent
import com.example.thuyhien.kotlinrxjava.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by thuyhien on 3/21/18.
 */
class KotlinApplication : DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder()
                .bindsApplication(this)
                .build()
        return appComponent
    }
}