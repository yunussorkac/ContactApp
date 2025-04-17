package com.app.contact.repo

import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact

class DetailScreenRepo {

    suspend fun getContactById(contactDao: ContactDao, id : Int): Contact? {
        return contactDao.getContactById(id)
    }

    suspend fun deleteContactById(contactDao: ContactDao, id : Int) {
        contactDao.deleteContactById(id)
    }

    suspend fun updateContact(contactDao: ContactDao, contact: Contact) {
        contactDao.updateContact(contact)
    }


}