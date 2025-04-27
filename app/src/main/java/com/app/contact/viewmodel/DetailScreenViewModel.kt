package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.model.Contact
import com.app.contact.domain.repo.DetailScreenRepo
import com.app.contact.domain.usecase.DeleteContactByIdUseCase
import com.app.contact.domain.usecase.GetContactByIdUseCase
import com.app.contact.domain.usecase.UpdateContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase,
    private val deleteContactByIdUseCase: DeleteContactByIdUseCase,
    private val updateContactUseCase: UpdateContactUseCase
) : ViewModel() {

    private val _contact = MutableStateFlow<Contact?>(null)
    val contact : StateFlow<Contact?> get() = _contact.asStateFlow()


    fun getContactById(id: Int) {
        viewModelScope.launch {
            getContactByIdUseCase(id)
                .onSuccess { contact ->
                    _contact.value = contact
                }
        }
    }

    fun deleteContactById(id: Int, onComplete: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            deleteContactByIdUseCase(id)
                .fold(
                    onSuccess = { onComplete(true) },
                    onFailure = { onComplete(false) }
                )
        }
    }

    fun updateContact(contact: Contact, onComplete: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            updateContactUseCase(contact)
                .fold(
                    onSuccess = { onComplete(true) },
                    onFailure = { onComplete(false) }
                )
        }
    }
}