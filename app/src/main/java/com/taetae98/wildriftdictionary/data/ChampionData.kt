package com.taetae98.wildriftdictionary.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class ChampionData private constructor() {
    companion object {
        private var instance: ChampionData? = null

        fun getInstance(): ChampionData {
            return instance ?: synchronized(this) {
                instance ?: ChampionData().also {
                    instance = it
                }
            }
        }
    }

    private val document by lazy {
        runBlocking(Dispatchers.IO) {
            try {
                Jsoup.connect("https://poro.gg/wildrift/champions/garen").get()
            } catch (e: Exception) {
                e.printStackTrace()
                Jsoup.parse("")
            }
        }
    }

    val champions by lazy {
        try {
            HashMap<String, Champion>().apply {
                document.getElementsByClass("wildrift-box__content").first().children().forEach {
                    val informationURL = it.attr("href")

                    val element = try { it.child(0) } catch (e: Exception) { Element("") }
                    val imageURL = "http:${element.attr("src")}"
                    val nameKr = element.attr("alt")
                    val nameEn = imageURL.substringAfter("champion/").substringBefore(".")
                    val headerImageURL = "https://poro.gg/images/lol/champion/splash-modified/$nameEn.jpg"
                    val splashImageURL = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${nameEn}_0.jpg"

                    Log.d("PASS", Champion(nameKr, nameEn, imageURL, headerImageURL, splashImageURL, informationURL).toString())
                    put(nameEn, Champion(nameKr, nameEn, imageURL, headerImageURL, splashImageURL, informationURL))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mapOf()
        }
    }
}