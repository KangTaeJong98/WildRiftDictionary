package com.taetae98.wildriftdictionary.data

data class Champion(
    var nameKr: String = "",
    var nameEn: String = "",
    var imageURL: String = "",
    var headerImageURL: String = "",
    var splashImageURL: String = "",
    var informationURL: String = "",
    var line: List<Line> = emptyList()
) {
    enum class Line {
        NONE, TOP, JUNGLE, MID, BOT, SUPPORTER
    }
}