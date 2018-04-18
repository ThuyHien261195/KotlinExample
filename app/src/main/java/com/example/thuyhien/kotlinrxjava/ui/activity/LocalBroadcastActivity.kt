package com.example.thuyhien.kotlinrxjava.ui.activity

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.model.Comment
import com.example.thuyhien.kotlinrxjava.presenter.LocalBroadcastPresenter
import com.example.thuyhien.kotlinrxjava.service.receiver.LocalBroadcastReceiver
import com.example.thuyhien.kotlinrxjava.ui.adapter.CommentAdapter
import com.example.thuyhien.kotlinrxjava.ui.listener.LocalBroadcastActivityListener
import com.example.thuyhien.kotlinrxjava.webSocket.LocalBroadcastWebSocketListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_comment.*
import javax.inject.Inject

class LocalBroadcastActivity : DaggerAppCompatActivity(), LocalBroadcastActivityListener {
    @Inject
    lateinit var localBroadcastPresenter: LocalBroadcastPresenter

    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentBroadcastReceiver: BroadcastReceiver
    private lateinit var connectStatusBroadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_broadcast)

        initView()
    }

    override fun onStart() {
        super.onStart()
        registerCommentBroadcastReceiver()
        registerConnectionStatusReceiver()
    }

    override fun onStop() {
        super.onStop()
        unRegisterBroadcastReceiver()
    }

    override fun onResume() {
        super.onResume()
        localBroadcastPresenter.connectServer()
    }

    override fun onPause() {
        super.onPause()
        localBroadcastPresenter.disconnectServer()
    }

    override fun showComment(comment: Comment) {
        commentAdapter.addComment(comment)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showConnectionStatus(status: String) {
        Toast.makeText(this, status, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        val commentlayoutManager = LinearLayoutManager(this)
        rvComments.layoutManager = commentlayoutManager

        commentAdapter = CommentAdapter(ArrayList<Comment>())
        rvComments.adapter = commentAdapter

        btnSend.setOnClickListener({
            localBroadcastPresenter.sendMessageToServer(editTextComment.text.toString())
        })
    }

    private fun registerCommentBroadcastReceiver() {
        commentBroadcastReceiver = LocalBroadcastReceiver(this)
        val intentFilter = IntentFilter(LocalBroadcastWebSocketListener.ACTION_SHOW_COMMENT)
        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.registerReceiver(commentBroadcastReceiver, intentFilter)
    }

    private fun registerConnectionStatusReceiver() {
        connectStatusBroadcastReceiver = LocalBroadcastReceiver(this)
        val intentFilter = IntentFilter(LocalBroadcastWebSocketListener.ACTION_SHOW_CONNECTION_STATUS)
        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.registerReceiver(connectStatusBroadcastReceiver, intentFilter)
    }

    private fun unRegisterBroadcastReceiver() {
        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.unregisterReceiver(commentBroadcastReceiver)
        localBroadcastManager.unregisterReceiver(connectStatusBroadcastReceiver)
    }
}
