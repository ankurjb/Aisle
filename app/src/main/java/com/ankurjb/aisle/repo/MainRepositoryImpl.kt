package com.ankurjb.aisle.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ankurjb.aisle.ApiMapper
import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.network.ApiClient
import com.ankurjb.aisle.utils.LocalStorage
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
    private val mapper: ApiMapper,
    private val dataStore: DataStore<Preferences>
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

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllProfiles() = withContext(Dispatchers.IO) {
        val authToken = runBlocking {
            dataStore.data.mapLatest { it[LocalStorage.AuthToken] }.first()
        }
        val result = apiClient.getProfileList(authToken)
        return@withContext if (result.isSuccessful) result.body() else null
    }
}
