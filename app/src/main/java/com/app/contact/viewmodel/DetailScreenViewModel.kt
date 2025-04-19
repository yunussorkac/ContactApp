package com.app.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.contact.model.Contact
import com.app.contact.repo.DetailScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel  @Inject constructor(
    private val detailScreenRepo: DetailScreenRepo
): ViewModel()  {


    suspend fun getContactById(id: Int): Contact? {
        return detailScreenRepo.getContactById(id)
    }

     fun deleteContactById(id : Int) {
         viewModelScope.launch {
             detailScreenRepo.deleteContactById(id)
         }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            detailScreenRepo.updateContact(contact)
        }
    }

}