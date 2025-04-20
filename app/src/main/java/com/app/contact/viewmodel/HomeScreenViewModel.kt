package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.model.Contact
import com.app.contact.domain.repo.HomeScreenRepo
import com.app.contact.domain.usecase.GetAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase
) : ViewModel() {

    private val _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactList: StateFlow<List<Contact>> = _contactList

    fun getAllContacts() {
        viewModelScope.launch {
            getAllContactsUseCase()
                .fold(
                    onSuccess = { contacts ->
                        _contactList.value = contacts
                    },
                    onFailure = {
                        _contactList.value = emptyList()
                    }
                )
        }
    }
}