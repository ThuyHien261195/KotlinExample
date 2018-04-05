package com.example.thuyhien.kotlinrxjava.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by thuyhien on 3/21/18.
 */
abstract class BaseViewHolder<E>(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
    abstract fun bind(data: E);

    fun getContext() = containerView.context
}