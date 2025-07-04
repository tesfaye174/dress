package com.example.dress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dress.data.model.*
import com.example.dress.data.repository.DressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val weatherData: WeatherData? = null,
    val todayRecommendation: DailyRecommendation? = null,
    val recentActivity: List<ClothingItem> = emptyList(),
    val error: String? = null
)

class HomeViewModel(
    private val repository: DressRepository = DressRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                // Carica dati meteo
                repository.getWeatherData().collect { weatherData ->
                    _uiState.value = _uiState.value.copy(weatherData = weatherData)
                }
                
                // Carica raccomandazioni
                repository.getDailyRecommendations().collect { recommendations ->
                    val todayRecommendation = recommendations.firstOrNull()
                    _uiState.value = _uiState.value.copy(
                        todayRecommendation = todayRecommendation,
                        recentActivity = todayRecommendation?.recommendedOutfit?.take(3) ?: emptyList()
                    )
                }
                
                _uiState.value = _uiState.value.copy(isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Errore nel caricamento dei dati: ${e.message}"
                )
            }
        }
    }
    
    fun refreshData() {
        loadInitialData()
    }
}
