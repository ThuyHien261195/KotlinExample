package com.example.thuyhien.kotlinrxjava.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.ui.activity.ExampleServiceActivity.Companion.TAG_FILE_PATH

/**
 * Created by thuyhien on 4/12/18.
 */
class PlayerInService : Service() {

    lateinit var filePath: String

    companion object {
        const val TAG_LOG_SERVICE = "2359Service"
        lateinit var mediaPlayer: MediaPlayer
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        mediaPlayer.reset()
        Log.e("2359", "OnCreateService")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        getData(intent!!)
        initMediaListener()
        Log.e(TAG_LOG_SERVICE, "OnStartCommand")
        return START_NOT_STICKY

        // START_STICKY: hanlde stop, effect pin
        // START_NOT_STICKY: stop after close completely app
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
        Log.e(TAG_LOG_SERVICE, "OnDestroy")
    }

    private fun getData(intent: Intent) {
        filePath = intent.getStringExtra(TAG_FILE_PATH)
    }

    private fun initMediaListener() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.example_song)
        } catch (e: Exception) {
            Log.e(TAG_LOG_SERVICE, "Cannot set data source ${e.message}")
        }
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { mp -> }
    }
}