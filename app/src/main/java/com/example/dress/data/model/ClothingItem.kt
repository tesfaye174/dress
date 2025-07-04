package com.example.dress.data.model

data class ClothingItem(
    val id: Int = 0,
    val name: String,
    val type: ClothingType,
    val color: String,
    val season: Season,
    val weatherCondition: List<WeatherCondition>,
    val style: Style,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false,
    val lastWorn: Long? = null,
    val cleanStatus: CleanStatus = CleanStatus.CLEAN
)

enum class ClothingType {
    SHIRT, PANTS, DRESS, JACKET, SHOES, ACCESSORIES, UNDERWEAR, SWEATER, SHORTS, SKIRT
}

enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER, ALL_SEASONS
}

enum class WeatherCondition {
    SUNNY, RAINY, CLOUDY, SNOWY, WINDY, HOT, COLD, MILD
}

enum class Style {
    CASUAL, FORMAL, SPORTY, ELEGANT, TRENDY, CLASSIC
}

enum class CleanStatus {
    CLEAN, DIRTY, NEEDS_WASH
}
