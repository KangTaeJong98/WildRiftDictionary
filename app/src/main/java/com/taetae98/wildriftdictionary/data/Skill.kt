package com.taetae98.wildriftdictionary.data

import java.io.Serializable

data class Skill(
    var imageURL: String = "",
    var name: String = "",
    var time: String = "",
    var costType: CostType = CostType.NONE,
    var cost: String = "",
    var description: String = ""
) : Serializable {
    enum class CostType {
        NONE, HP, MP, ENERGY, ANGER
    }
}