package com.app.contact.domain.repo

import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact
import javax.inject.Inject

class HomeScreenRepo @Inject constructor(
    private val contactDao: ContactDao

) {

    suspend fun getAllContacts(): List<Contact> {
        return contactDao.getAllContacts()
    }

    suspend fun searchContacts(query: String): List<Contact> {
        return contactDao.searchContacts("%$query%")
    }


}