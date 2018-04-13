package com.example.thuyhien.kotlinrxjava.ui.activity

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.service.PlayerInService
import com.example.thuyhien.kotlinrxjava.service.PlayerInService.Companion.LOG_TAG
import com.example.thuyhien.kotlinrxjava.view.ExampleServiceView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_service.*
import java.io.File

class ExampleServiceActivity : DaggerAppCompatActivity(), ExampleServiceView {

    companion object {
        val TAG_FILE_PATH = "FilePath";
    }

    var isPlaying = false;

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        btnPlay.setOnClickListener { view ->
            if (!isPlaying) {
                startService()
                Log.e(LOG_TAG, "Start play")
            } else {
                stopService()
            }
            isPlaying = !isPlaying
            updateUI()
        }
    }

    private fun startService() {
        val intent = Intent(this, PlayerInService::class.java)
        val uri = createBundle()
        intent.putExtra(TAG_FILE_PATH, uri.toString())
        startService(intent)
    }

    private fun stopService() {
        val intent = Intent(this, PlayerInService::class.java)
        stopService(intent)
    }

    private fun updateUI() {
        if (isPlaying) {
            btnPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)
        } else {
            btnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
        }
    }

    private fun createBundle() : Uri {
        val path = "android.resource://" + packageName + "/" + R.raw.example_song;
        return Uri.fromFile(File(path))
    }
}
