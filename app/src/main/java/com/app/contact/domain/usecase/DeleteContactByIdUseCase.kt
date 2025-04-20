package com.app.contact.domain.usecase

import com.app.contact.domain.repo.DetailScreenRepo
import javax.inject.Inject

class DeleteContactByIdUseCase @Inject constructor(
    private val repository: DetailScreenRepo
) {
    suspend operator fun invoke(id: Int): Result<Unit> {
        return try {
            repository.deleteContactById(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}