package com.example.thuyhien.kotlinrxjava.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer

/**
 * Created by thuyhien on 4/13/18.
 */
class BoundService : Service() {
    companion object {
        const val TAG_LOG_BOUND_SERVICE = "2359BoundService"
    }

    val timeStampBinder = TimeStampBinder()
    var countCheck = 0
    lateinit var chronometer: Chronometer


    override fun onCreate() {
        super.onCreate()
        Log.e(TAG_LOG_BOUND_SERVICE, "OnCreate")
        chronometer = Chronometer(this)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG_LOG_BOUND_SERVICE, "OnBind")
        return timeStampBinder
    }

    override fun onRebind(intent: Intent?) {
        Log.e(TAG_LOG_BOUND_SERVICE, "onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG_LOG_BOUND_SERVICE, "onUnbind")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG_LOG_BOUND_SERVICE, "onDestroy")
        chronometer.stop()
    }

    fun getTimestamp(): String {
        countCheck++
        val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
        val hours = (elapsedMillis / 3600000).toInt()
        val minutes = ((elapsedMillis - hours * 3600000) / 60000).toInt()
        val seconds = ((elapsedMillis - hours * 3600000 - minutes * 60000) / 1000).toInt()
        val millis = (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000).toInt()
        return "$hours: $minutes: $seconds: $millis countcheck: $countCheck"
    }


    inner class TimeStampBinder : Binder() {
        fun getService(): BoundService {
            return this@BoundService
        }
    }
}