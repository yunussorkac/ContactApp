package com.app.contact.domain.repo

import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact
import javax.inject.Inject

class DetailScreenRepo @Inject constructor(
    private val contactDao: ContactDao

){

    suspend fun getContactById(id : Int): Contact? {
        return contactDao.getContactById(id)
    }

    suspend fun deleteContactById(id : Int) {
        contactDao.deleteContactById(id)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.updateContact(contact)
    }


}