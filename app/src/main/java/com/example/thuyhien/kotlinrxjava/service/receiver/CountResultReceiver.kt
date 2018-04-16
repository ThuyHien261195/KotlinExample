package com.example.thuyhien.kotlinrxjava.service.receiver

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import com.example.thuyhien.kotlinrxjava.service.listener.CountReceiverListener

/**
 * Created by thuyhien on 4/13/18.
 */
open class CountResultReceiver(handler: Handler, val countReceiverListener: CountReceiverListener?):
        ResultReceiver(handler) {
    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        countReceiverListener?.let {
            countReceiverListener.onCountReceiver(resultCode, resultData)
        }
    }
}