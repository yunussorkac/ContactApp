package com.app.contact.domain.usecase

import com.app.contact.domain.repo.DetailScreenRepo
import com.app.contact.model.Contact
import javax.inject.Inject

class GetContactByIdUseCase @Inject constructor(
    private val repository: DetailScreenRepo
) {
    suspend operator fun invoke(id: Int): Result<Contact?> {
        return try {
            val contact = repository.getContactById(id)
            Result.success(contact)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}