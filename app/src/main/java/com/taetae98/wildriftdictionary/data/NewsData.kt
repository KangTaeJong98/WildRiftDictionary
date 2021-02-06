package com.taetae98.wildriftdictionary.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class NewsData private constructor() {
    companion object {
        private var instance: NewsData? = null

        fun getInstance(): NewsData {
            return instance ?: synchronized(this) {
                instance ?: NewsData().also {
                    instance = it
                }
            }
        }
    }

    private val document by lazy {
        runBlocking(Dispatchers.IO) {
            try {
                Jsoup.connect("https://wildrift.leagueoflegends.com/ko-kr/news/").get()
            } catch (e: Exception) {
                e.printStackTrace()
                Jsoup.parse("")
            }
        }
    }

    val news by lazy {
        try {
            ArrayList<News>().apply {
                document.getElementsByClass("articleCardWrapper-1JIOy").forEach {
                    val imageURL = try { it.getElementsByClass("image-NeGf2").first().attr("src") } catch (e: Exception) { e.printStackTrace(); "" }
                    val title = try { it.getElementsByClass("title--HVLV").first().text() } catch (e: Exception) { e.printStackTrace(); "" }
                    val url = it.attr("href").run {
                        return@run if (!contains("https")) {
                            "https://wildrift.leagueoflegends.com$this"
                        } else {
                            this
                        }
                    }

                    add(News(imageURL, title, url))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}