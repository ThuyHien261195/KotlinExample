package com.example.thuyhien.kotlinrxjava.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.ui.listener.CouponsActivityListener
import com.example.thuyhien.kotlinrxjava.ui.viewholder.CouponViewHolder

/**
 * Created by thuyhien on 3/21/18.
 */
class CouponAdapter(private val couponList : List<Coupon>, private val couponsActivityListener: CouponsActivityListener)
    : RecyclerView.Adapter<CouponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CouponViewHolder {
        val rowView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_store_coupon, parent, false)
        return CouponViewHolder(rowView, couponsActivityListener)
    }

    override fun getItemCount() = couponList.size

    override fun onBindViewHolder(holder: CouponViewHolder?, position: Int) {
        holder?.bind(couponList[position])
    }

}