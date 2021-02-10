package com.taetae98.wildriftdictionary.data

import java.io.Serializable

data class Spell(
    var imageURL: String = "",
    var name: String = "",
    var time: String = "",
    var description: String = ""
) : Serializable