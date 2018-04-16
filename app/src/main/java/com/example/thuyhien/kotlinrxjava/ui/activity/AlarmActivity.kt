package com.example.thuyhien.kotlinrxjava.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.service.receiver.AlarmReceiver
import com.example.thuyhien.kotlinrxjava.view.AlarmView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : DaggerAppCompatActivity(), AlarmView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        btnScheduleAlarm.setOnClickListener{
            scheduleAlarm()
        }

        btnCancelAlarm.setOnClickListener{
            cancelAlarm()
        }
    }

    private fun scheduleAlarm() {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val firstMillis = System.currentTimeMillis()
        val alarm = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                2000, pendingIntent)
    }

    private fun cancelAlarm() {
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarm = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarm.cancel(pendingIntent)
    }
}
