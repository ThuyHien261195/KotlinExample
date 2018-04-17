package com.example.thuyhien.kotlinrxjava.service.receiver

import android.content.Context
import android.content.Intent
import android.support.v4.content.WakefulBroadcastReceiver
import com.example.thuyhien.kotlinrxjava.service.AlarmService

/**
 * Created by thuyhien on 4/16/18.
 */
class BootBroadcastReceiver : WakefulBroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            val intent = Intent(context, AlarmService::class.java)
            context?.startService(intent)
        }
    }
}