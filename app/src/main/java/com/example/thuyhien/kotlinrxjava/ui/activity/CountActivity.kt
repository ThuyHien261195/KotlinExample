package com.example.thuyhien.kotlinrxjava.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.presenter.CountPresenter
import com.example.thuyhien.kotlinrxjava.service.CountIntentService
import com.example.thuyhien.kotlinrxjava.service.listener.CountReceiverListener
import com.example.thuyhien.kotlinrxjava.service.receiver.CountResultReceiver
import com.example.thuyhien.kotlinrxjava.view.CountView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_count.*
import javax.inject.Inject

class CountActivity : DaggerAppCompatActivity(), CountView {
    @Inject
    lateinit var countPresenter: CountPresenter
    lateinit var countResultReceiver: CountResultReceiver

    companion object {
        const val EXTRA_MAX_NUMBER = "MaxNumber"
        const val EXTRA_COUNT_RECEIVER = "CountReceiver"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        setupServiceReceiver()

        btnStartCount.setOnClickListener {
            countPresenter.startCount()
        }
    }

    override fun getMaxCountNumber(count: Int) {
        val intent = Intent(this, CountIntentService::class.java)
        intent.putExtra(EXTRA_MAX_NUMBER, 10)
        intent.putExtra(EXTRA_COUNT_RECEIVER, countResultReceiver)
        startService(intent)
    }

    private fun setupServiceReceiver() {
        countResultReceiver = CountResultReceiver(Handler(), object : CountReceiverListener {
            override fun onCountReceiver(resultCode: Int, resultData: Bundle?) {
                if (resultCode == Activity.RESULT_OK) {
                    val resultValue = resultData?.getInt(CountIntentService.EXTRA_RESULT_NUMBER)
                            ?: "Null"
                    tvCount.text = resultValue.toString()
                }
            }
        })
    }
}
