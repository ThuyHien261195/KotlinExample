package com.example.thuyhien.kotlinrxjava.contentProvider.listener

import com.example.thuyhien.kotlinrxjava.model.Contact

/**
 * Created by thuyhien on 4/20/18.
 */
interface ContactCallBackListener {
    fun onLoadContactSuccess(contactsList: ArrayList<Contact>)
}