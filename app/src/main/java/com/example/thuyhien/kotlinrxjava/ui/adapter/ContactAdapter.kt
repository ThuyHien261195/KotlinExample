package com.example.thuyhien.kotlinrxjava.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.extensions.inflateView
import com.example.thuyhien.kotlinrxjava.model.Contact
import com.example.thuyhien.kotlinrxjava.ui.viewholder.ContactViewHolder

/**
 * Created by thuyhien on 4/19/18.
 */
class ContactAdapter(private val contactList: ArrayList<Contact>) : RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val row = parent.inflateView(R.layout.item_contact)
        return ContactViewHolder(row)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    fun updateData(contactList: ArrayList<Contact>) {
        this.contactList.clear()
        this.contactList.addAll(contactList)
        notifyDataSetChanged()
    }
}