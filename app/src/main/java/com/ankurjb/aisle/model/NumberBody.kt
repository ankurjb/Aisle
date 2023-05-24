package com.ankurjb.aisle.model

import com.google.gson.annotations.SerializedName

data class NumberBody(
    @SerializedName("number")
    val number: String
)
