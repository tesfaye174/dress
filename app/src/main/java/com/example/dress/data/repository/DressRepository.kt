package com.example.dress.data.repository

import com.example.dress.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DressRepository {
    
    fun getWeatherData(): Flow<WeatherData> = flow {
        // Simulazione API meteo
        emit(WeatherData(
            temperature = 22.0,
            humidity = 65,
            condition = WeatherCondition.SUNNY,
            windSpeed = 5.2,
            location = "Roma, IT"
        ))
    }
    
    fun getClothingItems(): Flow<List<ClothingItem>> = flow {
        emit(getSampleClothingItems())
    }
    
    fun getDailyRecommendations(): Flow<List<DailyRecommendation>> = flow {
        emit(getSampleRecommendations())
    }
    
    private fun getSampleClothingItems(): List<ClothingItem> = listOf(
        ClothingItem(
            id = 1,
            name = "Camicia Bianca Elegante",
            type = ClothingType.SHIRT,
            color = "Bianco",
            season = Season.ALL_SEASONS,
            weatherCondition = listOf(WeatherCondition.SUNNY, WeatherCondition.CLOUDY),
            style = Style.FORMAL,
            isFavorite = true
        ),
        ClothingItem(
            id = 2,
            name = "Jeans Slim Fit",
            type = ClothingType.PANTS,
            color = "Blu Scuro",
            season = Season.ALL_SEASONS,
            weatherCondition = listOf(WeatherCondition.SUNNY, WeatherCondition.CLOUDY),
            style = Style.CASUAL,
            isFavorite = false
        ),
        ClothingItem(
            id = 3,
            name = "Giacca di Pelle",
            type = ClothingType.JACKET,
            color = "Nero",
            season = Season.AUTUMN,
            weatherCondition = listOf(WeatherCondition.COLD, WeatherCondition.WINDY),
            style = Style.TRENDY,
            isFavorite = true
        ),
        ClothingItem(
            id = 4,
            name = "Vestito Estivo",
            type = ClothingType.DRESS,
            color = "Rosa",
            season = Season.SUMMER,
            weatherCondition = listOf(WeatherCondition.HOT, WeatherCondition.SUNNY),
            style = Style.ELEGANT,
            isFavorite = false
        ),
        ClothingItem(
            id = 5,
            name = "Sneakers Sportive",
            type = ClothingType.SHOES,
            color = "Bianco",
            season = Season.ALL_SEASONS,
            weatherCondition = listOf(WeatherCondition.SUNNY, WeatherCondition.CLOUDY),
            style = Style.SPORTY,
            isFavorite = true
        ),
        ClothingItem(
            id = 6,
            name = "Maglione Lana",
            type = ClothingType.SWEATER,
            color = "Grigio",
            season = Season.WINTER,
            weatherCondition = listOf(WeatherCondition.COLD, WeatherCondition.SNOWY),
            style = Style.CASUAL,
            isFavorite = false
        ),
        ClothingItem(
            id = 7,
            name = "Shorts Estivi",
            type = ClothingType.SHORTS,
            color = "Beige",
            season = Season.SUMMER,
            weatherCondition = listOf(WeatherCondition.HOT, WeatherCondition.SUNNY),
            style = Style.CASUAL,
            isFavorite = true
        ),
        ClothingItem(
            id = 8,
            name = "Scarpe Eleganti",
            type = ClothingType.SHOES,
            color = "Nero",
            season = Season.ALL_SEASONS,
            weatherCondition = listOf(WeatherCondition.SUNNY, WeatherCondition.CLOUDY),
            style = Style.FORMAL,
            isFavorite = false
        )
    )
    
    private fun getSampleRecommendations(): List<DailyRecommendation> = listOf(
        DailyRecommendation(
            date = "Oggi",
            weather = WeatherData(
                temperature = 22.0,
                humidity = 65,
                condition = WeatherCondition.SUNNY,
                windSpeed = 5.2,
                location = "Roma, IT"
            ),
            recommendedOutfit = listOf(
                getSampleClothingItems()[0], // Camicia Bianca
                getSampleClothingItems()[1], // Jeans
                getSampleClothingItems()[4]  // Sneakers
            ),
            styleNote = "Perfetto per una giornata soleggiata! Look casual e confortevole per le attività quotidiane.",
            confidenceLevel = 0.85f
        ),
        DailyRecommendation(
            date = "Domani",
            weather = WeatherData(
                temperature = 18.0,
                humidity = 70,
                condition = WeatherCondition.CLOUDY,
                windSpeed = 8.0,
                location = "Roma, IT"
            ),
            recommendedOutfit = listOf(
                getSampleClothingItems()[5], // Maglione
                getSampleClothingItems()[1], // Jeans
                getSampleClothingItems()[2]  // Giacca
            ),
            styleNote = "Tempo nuvoloso e fresco. Porta una giacca leggera per stare al caldo!",
            confidenceLevel = 0.78f
        ),
        DailyRecommendation(
            date = "Dopodomani",
            weather = WeatherData(
                temperature = 25.0,
                humidity = 55,
                condition = WeatherCondition.SUNNY,
                windSpeed = 3.0,
                location = "Roma, IT"
            ),
            recommendedOutfit = listOf(
                getSampleClothingItems()[3], // Vestito Estivo
                getSampleClothingItems()[4]  // Sneakers
            ),
            styleNote = "Giornata calda e soleggiata. Look estivo e fresco, perfetto per uscire!",
            confidenceLevel = 0.92f
        )
    )
}
