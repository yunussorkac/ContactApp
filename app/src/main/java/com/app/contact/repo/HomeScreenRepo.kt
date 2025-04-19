package com.app.contact.repo

import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact
import javax.inject.Inject

class HomeScreenRepo @Inject constructor(
    private val contactDao: ContactDao

) {

    suspend fun getAllContacts(): List<Contact> {
        return contactDao.getAllContacts()
    }


}