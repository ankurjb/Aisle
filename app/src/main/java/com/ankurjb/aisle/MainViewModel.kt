package com.ankurjb.aisle

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.ankurjb.aisle.utils.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@HiltViewModel
class MainViewModel @Inject constructor(
    dataStore: DataStore<Preferences>
) : ViewModel() {

    val isAuthorised: Flow<Boolean> = dataStore.data.map {
        it[LocalStorage.AuthToken].isNullOrBlank().not()
    }

}
