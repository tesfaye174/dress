package com.example.dress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dress.data.model.DailyRecommendation
import com.example.dress.data.repository.DressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecommendationsUiState(
    val isLoading: Boolean = false,
    val recommendations: List<DailyRecommendation> = emptyList(),
    val error: String? = null
)

class RecommendationsViewModel(
    private val repository: DressRepository = DressRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecommendationsUiState())
    val uiState: StateFlow<RecommendationsUiState> = _uiState.asStateFlow()

    init {
        loadRecommendations()
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                repository.getDailyRecommendations().collect { recommendations ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        recommendations = recommendations
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Errore nel caricamento: ${e.message}"
                )
            }
        }
    }

    fun refreshRecommendations() {
        loadRecommendations()
    }

    fun applyRecommendation(recommendation: DailyRecommendation) {
        // Logica per applicare la raccomandazione
        // Ad esempio, segnare i vestiti come indossati
        viewModelScope.launch {
            // Implementa la logica per applicare la raccomandazione
            // Per ora, solo un placeholder
        }
    }
}
