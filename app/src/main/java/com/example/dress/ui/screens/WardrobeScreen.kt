package com.example.dress.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dress.data.model.*

package com.example.dress.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dress.data.model.*
import com.example.dress.ui.viewmodel.WardrobeViewModel
import com.example.dress.ui.viewmodel.FilterType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WardrobeScreen(
    onNavigateBack: () -> Unit,
    viewModel: WardrobeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Indietro")
            }
            Text(
                text = "Il Mio Guardaroba 👗",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Add new item */ }) {
                Icon(Icons.Default.Add, contentDescription = "Aggiungi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter chips
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        onClick = { viewModel.applyFilter(FilterType.ALL) },
                        label = { Text("Tutti") },
                        selected = uiState.selectedFilter == FilterType.ALL
                    )
                    FilterChip(
                        onClick = { viewModel.applyFilter(FilterType.FAVORITES) },
                        label = { Text("Preferiti") },
                        selected = uiState.selectedFilter == FilterType.FAVORITES
                    )
                    FilterChip(
                        onClick = { viewModel.applyFilter(FilterType.SHIRTS) },
                        label = { Text("Camicie") },
                        selected = uiState.selectedFilter == FilterType.SHIRTS
                    )
                }
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        onClick = { viewModel.applyFilter(FilterType.PANTS) },
                        label = { Text("Pantaloni") },
                        selected = uiState.selectedFilter == FilterType.PANTS
                    )
                    FilterChip(
                        onClick = { viewModel.applyFilter(FilterType.SHOES) },
                        label = { Text("Scarpe") },
                        selected = uiState.selectedFilter == FilterType.SHOES
                    )
                    FilterChip(
                        onClick = { viewModel.applyFilter(FilterType.JACKETS) },
                        label = { Text("Giacche") },
                        selected = uiState.selectedFilter == FilterType.JACKETS
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Loading state
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Clothing items list
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.filteredItems) { item ->
                    ClothingItemCard(
                        item = item,
                        onItemClick = { /* Navigate to detail */ },
                        onFavoriteClick = { viewModel.toggleFavorite(item.id) }
                    )
                }
            }
        }

        // Error state
        uiState.error?.let { error ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { /* Retry */ }) {
                        Text("Riprova")
                    }
                }
            ) {
                Text(error)
            }
        }
    }
}

@Composable
fun ClothingItemCard(
    item: ClothingItem,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon based on clothing type
                Icon(
                    imageVector = when (item.type) {
                        ClothingType.SHIRT -> Icons.Default.Checkroom
                        ClothingType.PANTS -> Icons.Default.Checkroom
                        ClothingType.DRESS -> Icons.Default.Checkroom
                        ClothingType.JACKET -> Icons.Default.Checkroom
                        ClothingType.SHOES -> Icons.Default.Checkroom
                        else -> Icons.Default.Checkroom
                    },
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "${item.type.name.lowercase().replaceFirstChar { it.uppercase() }} • ${item.color}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = item.style.name.lowercase().replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (item.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Preferito",
                    tint = if (item.isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
