package com.ankurjb.aisle.repo

import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.model.Profile

interface MainRepository {

    suspend fun phoneNumberLogin(phoneNumber: PhoneNumber): Boolean

    suspend fun verifyOtp(
        phoneNumber: PhoneNumber?,
        otp: String
    ): String?

    suspend fun getAllProfiles(): Profile?
}
