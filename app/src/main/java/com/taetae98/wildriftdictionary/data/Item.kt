package com.taetae98.wildriftdictionary.data

data class Item(
    var imageURL: String = "",
    var name: String = "",
    var cost: String = "",
    var ability: String = "",
    var description: String = "",
    var type: Type = Type.NONE,
    var level: Level = Level.NONE
) {
    enum class Type {
        PHYSICAL, MAGIC, DEFENSE, BOOTS, NONE
    }

    enum class Level {
        UPGRADED, MID, BASIC, ENCHANTMENT, NONE
    }
}