package com.app.contact.repo

import com.app.contact.dao.ContactDao
import com.app.contact.db.ContactDatabase
import com.app.contact.model.Contact

class AddContactScreenRepo {


    suspend fun insertContact(contact: Contact, contactDao: ContactDao) {
        contactDao.insertContact(contact)
    }



}