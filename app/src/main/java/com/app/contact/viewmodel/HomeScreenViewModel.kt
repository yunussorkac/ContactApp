package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.model.Contact
import com.app.contact.domain.repo.HomeScreenRepo
import com.app.contact.domain.usecase.GetAllContactsUseCase
import com.app.contact.domain.usecase.SearchContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val searchContactsUseCase: SearchContactsUseCase

) : ViewModel() {

    private val _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactList: StateFlow<List<Contact>> get() = _contactList.asStateFlow()

    private val _filteredList = MutableStateFlow<List<Contact>>(emptyList())
    val filteredList: StateFlow<List<Contact>> get() = _filteredList.asStateFlow()

    fun getAllContacts() {
        viewModelScope.launch {
            getAllContactsUseCase()
                .fold(
                    onSuccess = { contacts ->
                        _contactList.value = contacts
                        _filteredList.value = emptyList()
                    },
                    onFailure = {
                        _contactList.value = emptyList()
                        _filteredList.value = emptyList()
                    }
                )
        }
    }

    fun searchContacts(query: String) {
        viewModelScope.launch {
            _filteredList.value = if (query.isNotEmpty()) {
                searchContactsUseCase(query)
            } else {
                emptyList()
            }
        }
    }


}