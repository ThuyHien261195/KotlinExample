package com.example.thuyhien.kotlinrxjava.view

import com.example.thuyhien.kotlinrxjava.model.Contact

/**
 * Created by thuyhien on 4/20/18.
 */
interface ContactView {
    fun showContactList(contactList: ArrayList<Contact>)
}