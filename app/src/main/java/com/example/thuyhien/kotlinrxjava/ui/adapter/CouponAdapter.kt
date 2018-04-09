package com.example.thuyhien.kotlinrxjava.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.extension.inflateItemView
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.ui.listener.CouponsActivityListener
import com.example.thuyhien.kotlinrxjava.ui.viewholder.CouponViewHolder

/**
 * Created by thuyhien on 3/21/18.
 */
class CouponAdapter(private val couponList : List<Coupon>, private val couponsActivityListener: CouponsActivityListener)
    : RecyclerView.Adapter<CouponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val rowView = parent.inflateItemView(R.layout.item_store_coupon)
        val couponViewHolder = CouponViewHolder(rowView)
        rowView.setOnClickListener({view -> couponsActivityListener.onClickCoupon(couponList[couponViewHolder.adapterPosition])})
        return couponViewHolder
    }

    override fun getItemCount() = couponList.size

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        holder.bind(couponList[position])
    }
}