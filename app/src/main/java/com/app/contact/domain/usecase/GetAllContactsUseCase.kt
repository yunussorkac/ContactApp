package com.app.contact.domain.usecase

import com.app.contact.domain.repo.DetailScreenRepo
import com.app.contact.domain.repo.HomeScreenRepo
import com.app.contact.model.Contact
import javax.inject.Inject

class GetAllContactsUseCase @Inject constructor(
    private val repository: HomeScreenRepo
) {
    suspend operator fun invoke(): Result<List<Contact>> {
        return try {
            val contacts = repository.getAllContacts()
            Result.success(contacts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}