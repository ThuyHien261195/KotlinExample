package com.example.thuyhien.kotlinrxjava.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.service.BoundService
import com.example.thuyhien.kotlinrxjava.service.BoundService.Companion.TAG_LOG_BOUND_SERVICE
import com.example.thuyhien.kotlinrxjava.view.BoundServiceView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_bound_service.*

class BoundServiceActivity : DaggerAppCompatActivity(), BoundServiceView {
    lateinit var boundService: BoundService
    var serviceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)

        btnPrintTimeStamp.setOnClickListener {
            if (serviceBound) {
                tvTimeStamp.text = boundService.getTimestamp()
            }
        }

        btnStopService.setOnClickListener {
            if (serviceBound) {
                unbindService(serviceConnection)
                serviceBound = false
            }
            val intent = Intent(this, BoundService::class.java)
            stopService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BoundService::class.java)
        startService(intent)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (serviceBound) {
            unbindService(serviceConnection)
            serviceBound = false
        }
    }

    private var serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            serviceBound = false
            Log.e(TAG_LOG_BOUND_SERVICE, "onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val timeStampBinder = service as BoundService.TimeStampBinder
            boundService = timeStampBinder.getService()
            serviceBound = true
            Log.e(TAG_LOG_BOUND_SERVICE, "onServiceConnected")
        }
    }
}
