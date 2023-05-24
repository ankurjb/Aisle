package com.ankurjb.aisle.model

import com.google.gson.annotations.SerializedName

data class ResponsePhoneNumber(
    @SerializedName("status")
    val status: Boolean
)
