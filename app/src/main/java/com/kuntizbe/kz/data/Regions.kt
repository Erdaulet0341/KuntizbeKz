package com.kuntizbe.kz.data

data class Region(
    val nameKaz: String,
    val nameEng: String,
    val cities: List<City>
)

val REGIONS = listOf(
    Region(nameKaz = "Ақмола облысы", nameEng = "Akmola", listOf()),
    Region(nameKaz = "Ақтөбе облысы", nameEng = "Aqtobe", listOf()),
    Region(nameKaz = "Алматы облысы", nameEng = "Almaty", listOf()),
    Region(nameKaz = "Атырау облысы", nameEng = "Atyrau", listOf()),
    Region(nameKaz = "Батыс Қазақстан облысы", nameEng = "West Kazakhstan", listOf()),
    Region(nameKaz = "Жамбыл облысы", nameEng = "Dzhambul (Zhambyl)", listOf()),
    Region(nameKaz = "Қарағанды облысы", nameEng = "Karaganda", listOf()),
    Region(nameKaz = "Қостанай облысы", nameEng = "Qostanay", listOf()),
    Region(nameKaz = "Қызылорда облысы", nameEng = "Kyzylorda", listOf()),
    Region(nameKaz = "Маңғыстау облысы", nameEng = "Mangystau", listOf()),
    Region(
        nameKaz = "Павлодар облысы",
        nameEng = "Pavlodar",
        listOf(
            City(cityId = 8524, cityName = "Алексеевка"),
            City(cityId = 8525, cityName = "Павлодар"),
            City(cityName = "Екібастұз", cityId = 43827)
        )
    ),
    Region(nameKaz = "Солтүстік Қазақстан облысы", nameEng = "North Kazakhstan", listOf()),
    Region(nameKaz = "Түркістан облысы", nameEng = "South Kazakhstan", listOf()),
    Region(nameKaz = "Шығыс Қазақстан облысы", nameEng = "East Kazakhstan", listOf()),
)