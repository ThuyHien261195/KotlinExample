package com.example.thuyhien.kotlinrxjava.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.thuyhien.kotlinrxjava.service.AlarmService

/**
 * Created by thuyhien on 4/16/18.
 */
class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val REQUEST_CODE = 1
        const val ACTION = "Alarm"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val intent = Intent(context, AlarmService::class.java)
        context?.startService(intent)
    }
}