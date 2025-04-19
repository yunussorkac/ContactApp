package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.model.Contact
import com.app.contact.repo.AddContactScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactScreenViewModel  @Inject constructor(
    private val repo : AddContactScreenRepo

) : ViewModel() {



    fun insertContact(contact: Contact, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repo.insertContact(contact)
                onComplete(true)
            } catch (e: Exception) {
                onComplete(false)
            }
        }
    }


}