package com.taetae98.wildriftdictionary.data

data class Rune(
    var imageURL: String = "",
    var name: String = "",
    var type: Type = Type.NONE,
    var ability: String = "",
    var description: String = "",
) {
    enum class Type {
        KEY_STONE, DOMINATION, RESOLVE, INSPIRATION, NONE
    }
}