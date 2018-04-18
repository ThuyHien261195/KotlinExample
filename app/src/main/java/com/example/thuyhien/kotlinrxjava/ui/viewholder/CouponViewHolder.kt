package com.example.thuyhien.kotlinrxjava.ui.viewholder

import android.view.View
import com.example.thuyhien.kotlinrxjava.model.Coupon
import kotlinx.android.synthetic.main.item_store_coupon.*

/**
 * Created by thuyhien on 3/21/18.
 */
class CouponViewHolder(itemView: View) : BaseViewHolder<Coupon>(itemView) {

    override fun bind(data: Coupon) {
        tvStore.text = data.store
        tvInfo.text = data.coupon
        tvDate.text = data.expiryDate
        tvCode.text = data.couponCode
    }

}