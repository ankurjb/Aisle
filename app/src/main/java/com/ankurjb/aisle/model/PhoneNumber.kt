package com.ankurjb.aisle.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneNumber(
    val countryCode: String,
    val phoneNumber: String
) : Parcelable
