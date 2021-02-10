package com.taetae98.wildriftdictionary.data

import java.io.Serializable

data class Rune(
    var imageURL: String = "",
    var name: String = "",
    var type: Type = Type.NONE,
    var ability: String = "",
    var description: String = "",
) : Serializable {
    enum class Type {
        KEY_STONE, DOMINATION, RESOLVE, INSPIRATION, NONE
    }
}