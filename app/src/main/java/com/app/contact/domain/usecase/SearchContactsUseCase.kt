package com.app.contact.domain.usecase

import com.app.contact.domain.repo.HomeScreenRepo
import com.app.contact.model.Contact
import javax.inject.Inject

class SearchContactsUseCase @Inject constructor(
    private val homeScreenRepo: HomeScreenRepo
) {

    suspend operator fun invoke(query: String): List<Contact> {
        return homeScreenRepo.searchContacts(query)
    }
}