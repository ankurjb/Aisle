package com.ankurjb.aisle.model

import com.google.gson.annotations.SerializedName

data class Otp(
    @SerializedName("number")
    val number: String,
    @SerializedName("otp")
    val otp: String
)
