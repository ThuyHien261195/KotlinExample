package com.example.thuyhien.kotlinrxjava.presenter.impl

import com.example.thuyhien.kotlinrxjava.presenter.CountPresenter
import com.example.thuyhien.kotlinrxjava.ui.activity.CountActivity
import com.example.thuyhien.kotlinrxjava.view.CountView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by thuyhien on 4/13/18.
 */
class CountPresenterImpl
@Inject constructor(countView: CountView) : CountPresenter {
    private val countViewWeakRef = WeakReference<CountView>(countView)

    override fun startCount() {
        getCountView()?.getMaxCountNumber(10)
    }

    private fun getCountView() = countViewWeakRef.get()
}