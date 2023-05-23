package com.ankurjb.aisle.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneNumber(
    val countryCode: String,
    val number: String
) : Parcelable
