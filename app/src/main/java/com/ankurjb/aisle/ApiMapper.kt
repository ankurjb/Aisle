package com.ankurjb.aisle

import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.model.NumberBody
import com.ankurjb.aisle.model.Otp
import javax.inject.Inject

class ApiMapper @Inject constructor() {

    fun toNumber(phoneNumber: PhoneNumber) = NumberBody(
        number = toPhoneNumber(phoneNumber)
    )

    fun toOtp(
        phoneNumber: PhoneNumber?,
        otp: String
    ) = Otp(
        number = toPhoneNumber(phoneNumber),
        otp = otp
    )

    private fun toPhoneNumber(
        phoneNumber: PhoneNumber?
    ) = "${phoneNumber?.countryCode.orEmpty().trim()}${phoneNumber?.number.orEmpty().trim()}"
}
