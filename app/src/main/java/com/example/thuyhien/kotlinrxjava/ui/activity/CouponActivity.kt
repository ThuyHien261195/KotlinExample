package com.example.thuyhien.kotlinrxjava.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.extensions.gone
import com.example.thuyhien.kotlinrxjava.extensions.visible
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.presenter.CouponPresenter
import com.example.thuyhien.kotlinrxjava.ui.adapter.CouponAdapter
import com.example.thuyhien.kotlinrxjava.ui.listener.CouponsActivityListener
import com.example.thuyhien.kotlinrxjava.view.CouponView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_coupon.*
import javax.inject.Inject

class CouponActivity : DaggerAppCompatActivity(), CouponView, CouponsActivityListener {

    @Inject
    lateinit var couponPresenter: CouponPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)

        initView()
        couponPresenter.getCouponList()
    }

    private fun initView() {
        val couponLayoutManager = LinearLayoutManager(this)
        rvCoupon.layoutManager = couponLayoutManager
    }

    override fun displayCouponList(couponList: List<Coupon>) {
        val couponAdapter = CouponAdapter(couponList, this)
        rvCoupon.adapter = couponAdapter
    }

    override fun showError(error: String) {
        Toast.makeText(this, "Store coupon: $error", Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBarLoading.visible()
    }

    override fun hideLoading() {
        progressBarLoading.gone()
    }

    override fun onClickCoupon(coupon: Coupon) {
        Toast.makeText(this, "Store coupon: ${coupon.couponCode}", Toast.LENGTH_LONG).show()
    }
}
