package com.example.dress.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dress.data.model.ClothingType
import com.example.dress.data.model.WeatherCondition

object IconUtils {
    fun getIconForClothingType(type: ClothingType): ImageVector {
        return when (type) {
            ClothingType.SHIRT -> Icons.Default.Checkroom
            ClothingType.PANTS -> Icons.Default.Checkroom
            ClothingType.DRESS -> Icons.Default.Checkroom
            ClothingType.JACKET -> Icons.Default.Checkroom
            ClothingType.SHOES -> Icons.Default.Checkroom
            ClothingType.ACCESSORIES -> Icons.Default.Checkroom
            ClothingType.UNDERWEAR -> Icons.Default.Checkroom
            ClothingType.SWEATER -> Icons.Default.Checkroom
            ClothingType.SHORTS -> Icons.Default.Checkroom
            ClothingType.SKIRT -> Icons.Default.Checkroom
        }
    }
    
    fun getIconForWeatherCondition(condition: WeatherCondition): ImageVector {
        return when (condition) {
            WeatherCondition.SUNNY -> Icons.Default.WbSunny
            WeatherCondition.RAINY -> Icons.Default.CloudQueue
            WeatherCondition.CLOUDY -> Icons.Default.Cloud
            WeatherCondition.SNOWY -> Icons.Default.AcUnit
            WeatherCondition.WINDY -> Icons.Default.Air
            WeatherCondition.HOT -> Icons.Default.Thermostat
            WeatherCondition.COLD -> Icons.Default.AcUnit
            WeatherCondition.MILD -> Icons.Default.Thermostat
        }
    }
}
