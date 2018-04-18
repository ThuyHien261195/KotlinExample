package com.example.thuyhien.kotlinrxjava.service

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.WakefulBroadcastReceiver
import android.util.Log

/**
 * Created by thuyhien on 4/16/18.
 */
class BootService : IntentService("BootService") {
    companion object {
        const val TAG_LOG_BOOT_SERVICE = "2359BootService"
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG_LOG_BOOT_SERVICE, "OnHandleIntent boot service")
        WakefulBroadcastReceiver.completeWakefulIntent(intent)
    }
}