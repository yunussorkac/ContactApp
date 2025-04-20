package com.app.contact.domain.usecase

import com.app.contact.domain.repo.AddContactScreenRepo
import com.app.contact.model.Contact
import javax.inject.Inject

class AddContactUseCase @Inject constructor(
    private val repository: AddContactScreenRepo
) {
    suspend operator fun invoke(contact: Contact): Result<Unit> {
        return try {
            repository.insertContact(contact)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}