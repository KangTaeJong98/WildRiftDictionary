package com.taetae98.wildriftdictionary.data

import java.io.Serializable

data class News(
    var imageURL: String = "",
    var title: String = "",
    var url: String = ""
) : Serializable