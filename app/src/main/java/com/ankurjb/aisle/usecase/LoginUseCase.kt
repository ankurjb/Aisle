package com.ankurjb.aisle.usecase

import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.repo.MainRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke(
        phoneNumber: PhoneNumber
    ) = repository.phoneNumberLogin(phoneNumber)

}
