package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact
import com.app.contact.repo.DetailScreenRepo
import kotlinx.coroutines.launch

class DetailScreenViewModel : ViewModel()  {

    private val detailScreenRepo = DetailScreenRepo()

    suspend fun getContactById(contactDao: ContactDao,id : Int): Contact? {
        return detailScreenRepo.getContactById(contactDao,id)
    }

     fun deleteContactById(contactDao: ContactDao, id : Int) {
         viewModelScope.launch {
             detailScreenRepo.deleteContactById(contactDao, id)
         }
    }

    fun updateContact(contactDao: ContactDao, contact: Contact) {
        viewModelScope.launch {
            detailScreenRepo.updateContact(contactDao, contact)
        }
    }

}