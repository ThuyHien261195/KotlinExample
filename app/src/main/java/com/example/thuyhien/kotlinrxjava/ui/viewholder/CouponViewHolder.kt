package com.example.thuyhien.kotlinrxjava.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.thuyhien.kotlinrxjava.model.Coupon
import com.example.thuyhien.kotlinrxjava.ui.listener.CouponsActivityListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_store_coupon.*
import java.lang.ref.WeakReference

/**
 * Created by thuyhien on 3/21/18.
 */
class CouponViewHolder(containerView : View)
    : BaseViewHolder<Coupon>(containerView) {
    override fun bind(data: Coupon) {
        tvStore.text = data.store
        tvInfo.text = data.coupon
        tvDate.text = data.expiryDate
        tvCode.text = data.couponCode
    }
}