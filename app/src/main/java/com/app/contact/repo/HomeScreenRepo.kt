package com.app.contact.repo

import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact

class HomeScreenRepo {

    suspend fun getAllContacts(contactDao: ContactDao): List<Contact> {
        return contactDao.getAllContacts()
    }


}