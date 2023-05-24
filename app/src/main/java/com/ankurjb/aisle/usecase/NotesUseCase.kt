package com.ankurjb.aisle.usecase

import com.ankurjb.aisle.repo.MainRepository
import javax.inject.Inject

class NotesUseCase @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke() = repository.getAllProfiles()
}
