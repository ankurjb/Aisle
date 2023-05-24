package com.ankurjb.aisle.repo

import com.ankurjb.aisle.ApiMapper
import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.network.ApiClient
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
    private val mapper: ApiMapper
) : MainRepository {

    override suspend fun phoneNumberLogin(
        phoneNumber: PhoneNumber
    ) = withContext(Dispatchers.IO) {
        val result = apiClient.phoneNumberLogin(mapper.toNumber(phoneNumber))
        return@withContext if (result.isSuccessful) result.body()?.status ?: false else false
    }

    override suspend fun verifyOtp(
        phoneNumber: PhoneNumber?,
        otp: String
    ) = withContext(Dispatchers.IO) {
        val result = apiClient.verifyOtp(mapper.toOtp(phoneNumber, otp))
        return@withContext if (result.isSuccessful) result.body()?.token else null
    }

    override suspend fun getAllProfiles() {
        apiClient.getProfileList()
    }
}
