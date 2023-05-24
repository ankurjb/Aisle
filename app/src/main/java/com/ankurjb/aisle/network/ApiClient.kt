package com.ankurjb.aisle.network

import com.ankurjb.aisle.model.NumberBody
import com.ankurjb.aisle.model.Otp
import com.ankurjb.aisle.model.Profile
import com.ankurjb.aisle.model.ResponseOtp
import com.ankurjb.aisle.model.ResponsePhoneNumber
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @POST("users/phone_number_login")
    suspend fun phoneNumberLogin(
        @Body number: NumberBody
    ): Response<ResponsePhoneNumber>

    @POST("users/verify_otp")
    suspend fun verifyOtp(
        @Body otp: Otp
    ): Response<ResponseOtp>

    @GET("users/test_profile_list")
    suspend fun getProfileList(): Response<Profile>
}
