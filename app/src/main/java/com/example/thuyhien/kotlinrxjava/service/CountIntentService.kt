package com.example.thuyhien.kotlinrxjava.service

import android.app.Activity.RESULT_OK
import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import com.example.thuyhien.kotlinrxjava.ui.activity.CountActivity
import com.example.thuyhien.kotlinrxjava.ui.activity.CountActivity.Companion.EXTRA_MAX_NUMBER

/**
 * Created by thuyhien on 4/13/18.
 */
class CountIntentService : IntentService("CountDownService") {
    private var maxNumber = 0

    companion object {
        const val TAG_LOG_INTENT_SERVICE = "2359IntentService"
        const val EXTRA_RESULT_NUMBER = "ResultNumber"
    }

    override fun onHandleIntent(intent: Intent?) {
        getData(intent)

        val resultReceiver = intent?.getParcelableExtra<ResultReceiver>(CountActivity.EXTRA_COUNT_RECEIVER)
        for (i in 0..maxNumber) {
            Thread.sleep(1000)
            val bundle = Bundle()
            bundle.putInt(EXTRA_RESULT_NUMBER, i)
            resultReceiver?.send(RESULT_OK, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG_LOG_INTENT_SERVICE, "OnDestroy")
    }

    private fun getData(intent: Intent?) {
        maxNumber = intent?.getIntExtra(EXTRA_MAX_NUMBER, 0) ?: 0
    }
}