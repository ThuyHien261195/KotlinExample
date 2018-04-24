package com.example.thuyhien.kotlinrxjava.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.extensions.inflateView
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.ui.listener.CouponsActivityListener
import com.example.thuyhien.kotlinrxjava.ui.viewholder.CouponViewHolder

/**
 * Created by thuyhien on 3/21/18.
 */
class CouponAdapter(private val couponList: List<Coupon>, private val couponsActivityListener: CouponsActivityListener) : RecyclerView.Adapter<CouponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val vh = CouponViewHolder(parent.inflateView(R.layout.item_store_coupon))
        vh.itemView.setOnClickListener {
            val coupon = couponList[vh.adapterPosition]
            couponsActivityListener.onClickCoupon(coupon)
        }
        return vh
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) = holder.bind(couponList[position])

    override fun getItemCount() = couponList.size
}