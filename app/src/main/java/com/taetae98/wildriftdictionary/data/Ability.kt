package com.taetae98.wildriftdictionary.data

data class Ability(
    var key: String = "",
    var value: String = "",
    var type: Type = Type.NORMAL
) {
    enum class Type {
        NORMAL, SUB
    }
}