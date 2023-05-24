package com.ankurjb.aisle.usecase

import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.repo.MainRepository
import javax.inject.Inject

class OtpUseCase @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke(
        phoneNumber: PhoneNumber?,
        otp: String
    ) = repository.verifyOtp(phoneNumber, otp)

}
