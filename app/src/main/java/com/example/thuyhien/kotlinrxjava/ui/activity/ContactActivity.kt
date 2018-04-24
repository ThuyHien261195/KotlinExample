package com.example.thuyhien.kotlinrxjava.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.model.Contact
import com.example.thuyhien.kotlinrxjava.presenter.ContactPresenter
import com.example.thuyhien.kotlinrxjava.ui.adapter.ContactAdapter
import com.example.thuyhien.kotlinrxjava.view.ContactView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_contact.*
import javax.inject.Inject

class ContactActivity : DaggerAppCompatActivity(), ContactView {
    @Inject
    lateinit var contactPresenter: ContactPresenter

    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        initView()
        contactPresenter.getContactList()
    }

    override fun showContactList(contactList: ArrayList<Contact>) {
        contactAdapter.updateData(contactList)
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(this)
        rvContact.layoutManager = linearLayoutManager
        contactAdapter = ContactAdapter(ArrayList<Contact>())
        rvContact.adapter = contactAdapter
    }
}
