package com.app.contact.domain.usecase

import com.app.contact.domain.repo.DetailScreenRepo
import com.app.contact.model.Contact
import javax.inject.Inject

class UpdateContactUseCase @Inject constructor(
    private val repository: DetailScreenRepo
) {
    suspend operator fun invoke(contact: Contact): Result<Unit> {
        return try {
            repository.updateContact(contact)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}