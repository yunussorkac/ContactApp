package com.app.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact
import com.app.contact.repo.AddContactScreenRepo
import kotlinx.coroutines.launch

class AddContactScreenViewModel : ViewModel() {

    private val repo = AddContactScreenRepo()


    fun insertContact(contact: Contact, contactDao: ContactDao, onComplete : (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repo.insertContact(contact, contactDao)
                onComplete(true)
            } catch (e: Exception) {
                onComplete(false)
            }
        }
    }


}