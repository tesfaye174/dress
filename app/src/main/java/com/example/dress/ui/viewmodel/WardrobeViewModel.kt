package com.example.dress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dress.data.model.*
import com.example.dress.data.repository.DressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WardrobeUiState(
    val isLoading: Boolean = false,
    val clothingItems: List<ClothingItem> = emptyList(),
    val filteredItems: List<ClothingItem> = emptyList(),
    val selectedFilter: FilterType = FilterType.ALL,
    val error: String? = null
)

enum class FilterType {
    ALL, FAVORITES, SHIRTS, PANTS, SHOES, DRESSES, JACKETS
}

class WardrobeViewModel(
    private val repository: DressRepository = DressRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(WardrobeUiState())
    val uiState: StateFlow<WardrobeUiState> = _uiState.asStateFlow()

    init {
        loadClothingItems()
    }

    private fun loadClothingItems() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                repository.getClothingItems().collect { items ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        clothingItems = items,
                        filteredItems = items
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

    fun applyFilter(filterType: FilterType) {
        val currentItems = _uiState.value.clothingItems
        val filteredItems = when (filterType) {
            FilterType.ALL -> currentItems
            FilterType.FAVORITES -> currentItems.filter { it.isFavorite }
            FilterType.SHIRTS -> currentItems.filter { it.type == ClothingType.SHIRT }
            FilterType.PANTS -> currentItems.filter { it.type == ClothingType.PANTS }
            FilterType.SHOES -> currentItems.filter { it.type == ClothingType.SHOES }
            FilterType.DRESSES -> currentItems.filter { it.type == ClothingType.DRESS }
            FilterType.JACKETS -> currentItems.filter { it.type == ClothingType.JACKET }
        }
        
        _uiState.value = _uiState.value.copy(
            filteredItems = filteredItems,
            selectedFilter = filterType
        )
    }

    fun toggleFavorite(itemId: Int) {
        val currentItems = _uiState.value.clothingItems
        val updatedItems = currentItems.map { item ->
            if (item.id == itemId) {
                item.copy(isFavorite = !item.isFavorite)
            } else {
                item
            }
        }
        
        _uiState.value = _uiState.value.copy(
            clothingItems = updatedItems,
            filteredItems = applyCurrentFilter(updatedItems)
        )
    }
    
    private fun applyCurrentFilter(items: List<ClothingItem>): List<ClothingItem> {
        return when (_uiState.value.selectedFilter) {
            FilterType.ALL -> items
            FilterType.FAVORITES -> items.filter { it.isFavorite }
            FilterType.SHIRTS -> items.filter { it.type == ClothingType.SHIRT }
            FilterType.PANTS -> items.filter { it.type == ClothingType.PANTS }
            FilterType.SHOES -> items.filter { it.type == ClothingType.SHOES }
            FilterType.DRESSES -> items.filter { it.type == ClothingType.DRESS }
            FilterType.JACKETS -> items.filter { it.type == ClothingType.JACKET }
        }
    }
}
