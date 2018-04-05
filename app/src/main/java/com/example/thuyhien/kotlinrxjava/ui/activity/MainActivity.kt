package com.example.thuyhien.kotlinrxjava.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.presenter.CouponPresenter
import com.example.thuyhien.kotlinrxjava.ui.adapter.CouponAdapter
import com.example.thuyhien.kotlinrxjava.ui.listener.CouponsActivityListener
import com.example.thuyhien.kotlinrxjava.view.CouponView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), CouponView, CouponsActivityListener {
    @Inject
    lateinit var couponPresenter: CouponPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView();
        couponPresenter.getCouponList()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.test_web_socket -> {
                val intent = Intent(this, CommentActivity::class.java)
                startActivity(intent)
            }
            else -> return true
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun displayCouponList(couponList: List<Coupon>) {
        val couponAdapter = CouponAdapter(couponList, this)
        rvCoupon.adapter = couponAdapter
    }

    override fun showError(error: String) {
        Toast.makeText(this, "Store coupon: " + error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBarLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarLoading.visibility = View.GONE
    }

    override fun onClickCoupon(coupon: Coupon) {
        Toast.makeText(this, "Store coupon: " + coupon.couponCode, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        val couponLayoutManager = LinearLayoutManager(this)
        rvCoupon.layoutManager = couponLayoutManager
    }
}
