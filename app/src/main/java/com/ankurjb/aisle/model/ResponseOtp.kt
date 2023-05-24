package com.ankurjb.aisle.model

import com.google.gson.annotations.SerializedName

data class ResponseOtp(
    @SerializedName("token")
    val token: String
)
