package com.example.thuyhien.kotlinrxjava.presenter.impl

import android.content.Context
import com.example.thuyhien.kotlinrxjava.contentProvider.ContactLoader
import com.example.thuyhien.kotlinrxjava.contentProvider.listener.ContactCallBackListener
import com.example.thuyhien.kotlinrxjava.model.Contact
import com.example.thuyhien.kotlinrxjava.presenter.ContactPresenter
import com.example.thuyhien.kotlinrxjava.view.ContactView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by thuyhien on 4/19/18.
 */
class ContactPresenterImpl
@Inject constructor(contactView: ContactView,
                    private val context: Context) : ContactPresenter {
    private val contactViewWeakRef = WeakReference<ContactView>(contactView)

    override fun getContactList() {
        ContactLoader.loadContact(context, object : ContactCallBackListener {
            override fun onLoadContactSuccess(contactsList: ArrayList<Contact>) {
                getContactView()?.showContactList(contactsList)
            }
        })
    }

    private fun getContactView() = contactViewWeakRef.get()

}