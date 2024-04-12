package com.kuntizbe.kz.screens.menu.menuItemScreens
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _countriesList = listOf(
        Country("kaz", "Kaz"),
        Country("rus", "Rus")
    )

    val filteredCountries: StateFlow<List<Country>> = _searchText
        .map { text ->
            if (text.isBlank()) {
                _countriesList
            } else {
                _countriesList.filter { country ->
                    country.name.uppercase().contains(text.trim().uppercase())
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _countriesList
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToggleSearch(active: Boolean) {
        _isSearching.value = active
        if (!active) {
            onSearchTextChange("")
        }
    }
}


@Immutable
data class Country(val code: String, val name: String)