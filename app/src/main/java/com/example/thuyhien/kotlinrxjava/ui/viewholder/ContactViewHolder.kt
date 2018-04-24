package com.example.thuyhien.kotlinrxjava.ui.viewholder

import android.net.Uri
import android.view.View
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.model.Contact
import kotlinx.android.synthetic.main.item_contact.*

/**
 * Created by thuyhien on 4/19/18.
 */
class ContactViewHolder(containerView: View) : BaseViewHolder<Contact>(containerView) {
    override fun bind(data: Contact) {
        if (data.avatarPath == null || data.avatarPath.isEmpty()) {
            ivAvatar.setImageResource(R.mipmap.ic_launcher)
        } else {
            ivAvatar.setImageURI(Uri.parse(data.avatarPath))
        }
        tvContactName.text = data.contactName
    }
}