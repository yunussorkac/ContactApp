package com.app.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact
import com.app.contact.repo.HomeScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel  @Inject constructor(
    private val repo : HomeScreenRepo

) : ViewModel() {


    private val _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactList : StateFlow<List<Contact>> get() = _contactList



    fun getAllContacts() {
        viewModelScope.launch {
            val contacts = repo.getAllContacts()
            _contactList.value = contacts
        }
    }

}