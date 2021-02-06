package com.taetae98.wildriftdictionary.data

data class News(
    var imageURL: String = "",
    var title: String = "",
    var description: String = "",
    var date: String = "",
    var type: Type = Type.NORMAL
) {
    enum class Type {
        SPECIAL, NORMAL, YOUTUBE
    }
}