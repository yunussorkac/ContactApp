package com.app.contact.repo

import com.app.contact.dao.ContactDao
import com.app.contact.db.ContactDatabase
import com.app.contact.model.Contact
import javax.inject.Inject

class AddContactScreenRepo @Inject constructor(
    private val contactDao: ContactDao

){


    suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact)
    }



}