package com.example.thuyhien.kotlinrxjava.ui.activity

import android.content.BroadcastReceiver
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.thuyhien.kotlinrxjava.EventBus.ConnectionEvent
import com.example.thuyhien.kotlinrxjava.EventBus.MessageEvent
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.model.Comment
import com.example.thuyhien.kotlinrxjava.presenter.CommentPresenter
import com.example.thuyhien.kotlinrxjava.ui.adapter.CommentAdapter
import com.example.thuyhien.kotlinrxjava.view.CommentView
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.item_comment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class CommentActivity : DaggerAppCompatActivity(), CommentView {
    @Inject
    lateinit var commentPresenter: CommentPresenter

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        initView()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        commentPresenter.connectServer()
    }

    override fun onPause() {
        super.onPause()
        commentPresenter.disconnectServer()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        commentAdapter.addComment(event.comment)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onConnectionEvent(event: ConnectionEvent) {
        Toast.makeText(this, event.status, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        val commentlayoutManager = LinearLayoutManager(this)
        rvComments.layoutManager = commentlayoutManager

        commentAdapter = CommentAdapter(ArrayList<Comment>())
        rvComments.adapter = commentAdapter

        btnSend.setOnClickListener({
           commentPresenter.sendMessageToServer(editTextComment.text.toString())
        })
    }
}
