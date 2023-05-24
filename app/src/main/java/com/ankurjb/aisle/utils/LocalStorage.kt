package com.ankurjb.aisle.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object LocalStorage {
    val AuthToken = stringPreferencesKey("auth_token")
}
