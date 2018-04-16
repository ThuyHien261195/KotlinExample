package com.example.thuyhien.kotlinrxjava.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Created by thuyhien on 4/16/18.
 */
class AlarmService : IntentService("AlarmService"){

    companion object {
        const val TAG_LOG_ALARM_SERVICE = "2359BroadcastReceiver"
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG_LOG_ALARM_SERVICE, "OnHandleIntent Alarm service")
    }

}