package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.model.Contact
import com.app.contact.domain.repo.AddContactScreenRepo
import com.app.contact.domain.usecase.AddContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactScreenViewModel @Inject constructor(
    private val addContactUseCase: AddContactUseCase
) : ViewModel() {

    fun insertContact(contact: Contact, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            addContactUseCase(contact)
                .fold(
                    onSuccess = { onComplete(true) },
                    onFailure = { onComplete(false) }
                )
        }
    }
}