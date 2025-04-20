package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.model.Contact
import com.app.contact.domain.repo.DetailScreenRepo
import com.app.contact.domain.usecase.DeleteContactByIdUseCase
import com.app.contact.domain.usecase.GetContactByIdUseCase
import com.app.contact.domain.usecase.UpdateContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase,
    private val deleteContactByIdUseCase: DeleteContactByIdUseCase,
    private val updateContactUseCase: UpdateContactUseCase
) : ViewModel() {

    suspend fun getContactById(id: Int): Contact? {
        return getContactByIdUseCase(id).getOrNull()
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