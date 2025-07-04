package com.example.dress.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuickActionsRow(
    onWardrobeClick: () -> Unit,
    onRecommendationsClick: () -> Unit,
    onAddClothingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Wardrobe Button
        ElevatedButton(
            onClick = onWardrobeClick,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Checkroom,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guardaroba")
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Recommendations Button
        ElevatedButton(
            onClick = onRecommendationsClick,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Lightbulb,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Consigli")
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Add Clothing Button
        ElevatedButton(
            onClick = onAddClothingClick,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Aggiungi")
        }
    }
}
