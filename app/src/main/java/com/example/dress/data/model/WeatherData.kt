package com.example.dress.data.model

data class WeatherData(
    val temperature: Double,
    val humidity: Int,
    val condition: WeatherCondition,
    val windSpeed: Double,
    val location: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class DailyRecommendation(
    val date: String,
    val weather: WeatherData,
    val recommendedOutfit: List<ClothingItem>,
    val styleNote: String,
    val confidenceLevel: Float
)
