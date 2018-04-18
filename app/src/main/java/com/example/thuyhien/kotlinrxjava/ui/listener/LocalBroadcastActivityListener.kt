package com.example.thuyhien.kotlinrxjava.ui.listener

import com.example.thuyhien.kotlinrxjava.model.Comment
import com.example.thuyhien.kotlinrxjava.model.StoreCoupons

/**
 * Created by thuyhien on 4/17/18.
 */
interface LocalBroadcastActivityListener {
    fun showComment(comment: Comment)
    fun showConnectionStatus(status: String)
    fun showError(error: String)
}