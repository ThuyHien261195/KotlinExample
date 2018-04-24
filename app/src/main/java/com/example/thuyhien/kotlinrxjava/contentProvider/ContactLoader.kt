package com.example.thuyhien.kotlinrxjava.contentProvider

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import com.example.thuyhien.kotlinrxjava.contentProvider.listener.ContactCallBackListener
import com.example.thuyhien.kotlinrxjava.model.Contact
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by thuyhien on 4/19/18.
 */
class ContactLoader {
    companion object {
        fun loadContact(context: Context?, contactCallBackListener: ContactCallBackListener) {
            doAsync {
                val projectionFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.PHOTO_URI)
                val cursor = context?.contentResolver?.query(ContactsContract.Contacts.CONTENT_URI,
                        projectionFields, null, null, null)
                val contactList = getContactList(cursor)
                cursor?.close()
                uiThread {
                    contactCallBackListener.onLoadContactSuccess(contactList)
                }
            }
        }

        private fun getContactList(cursor: Cursor?): ArrayList<Contact> {
            val contactList = ArrayList<Contact>()
            cursor?.let {
                val nameIdx = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)
                val imgIdx = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI)
                while (cursor.moveToNext()) {
                    val contact = Contact(cursor.getString(nameIdx), cursor.getString(imgIdx))
                    contactList.add(contact)
                }
            }
            return contactList
        }
    }
}