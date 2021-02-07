package com.taetae98.wildriftdictionary.data

import android.util.Log
import com.taetae98.wildriftdictionary.singleton.LocaleManager
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
                Jsoup.connect("https://poro.gg/wildrift/champions/garen?hl=${LocaleManager.getPoroGGLocale()}").get()
            } catch (e: Exception) {
                e.printStackTrace()
                Jsoup.parse("")
            }
        }
    }

    val champions by lazy {
        val map = try {
            HashMap<String, List<Champion.Line>>().apply {
                runBlocking(Dispatchers.IO) {
                    Jsoup.connect("https://poro.gg/champions?hl=${LocaleManager.getPoroGGLocale()}").get().getElementsByClass("champion-list__item").forEach {
                        val nameLocale = it.getElementsByClass("champion-list__item__name").first().text().trim()
                        val lines = it.attr("data-positions").split(",").map { line ->
                            when(line) {
                                "top" -> {
                                    Champion.Line.TOP
                                }
                                "jng" -> {
                                    Champion.Line.JUNGLE
                                }
                                "mid" -> {
                                    Champion.Line.MID
                                }
                                "adc" -> {
                                    Champion.Line.BOT
                                }
                                "sup" -> {
                                    Champion.Line.SUPPORTER
                                }
                                else ->  {
                                    Champion.Line.NONE
                                }
                            }
                        }

                        put(nameLocale, lines)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mapOf()
        }
        Log.d("PASS", "Line : $map")
        try {
            HashMap<String, Champion>().apply {
                document.getElementsByClass("wildrift-box__content").first().children().forEach {
                    val informationURL = it.attr("href") + "?hl=${LocaleManager.getPoroGGLocale()}".trim()

                    val element = try { it.child(0) } catch (e: Exception) { Element("") }
                    val imageURL = "http:${element.attr("src")}".trim()
                    val nameLocale = element.attr("alt").trim()
                    val nameEn = imageURL.substringAfter("champion/").substringBefore(".").trim()
                    val headerImageURL = "https://poro.gg/images/lol/champion/splash-modified/$nameEn.jpg"
                    val splashImageURL = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${nameEn}_0.jpg"

                    put(nameLocale, Champion(nameLocale, nameEn, imageURL, headerImageURL, splashImageURL, informationURL, map.getOrDefault(nameLocale, emptyList())).also { champion ->
                        Log.d("PASS", champion.toString())
                    })
                }
            }.also {
                Log.d("PASS", "Champion : $it")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mapOf()
        }
    }
}