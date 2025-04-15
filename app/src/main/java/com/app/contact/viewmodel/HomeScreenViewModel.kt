package com.app.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact
import com.app.contact.repo.HomeScreenRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val repo = HomeScreenRepo()

    private val _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactList : StateFlow<List<Contact>> get() = _contactList



    fun getAllContacts(contactDao: ContactDao) {
        viewModelScope.launch {
            val contacts = repo.getAllContacts(contactDao)
            _contactList.value = contacts
        }
    }

}