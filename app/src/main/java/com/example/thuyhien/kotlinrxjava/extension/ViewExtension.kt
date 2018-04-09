package com.example.thuyhien.kotlinrxjava.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by thuyhien on 4/9/18.
 */

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ViewGroup.inflateItemView(resourceLayout: Int) : View {
    return LayoutInflater.from(this.context).inflate(resourceLayout, this, false)
}