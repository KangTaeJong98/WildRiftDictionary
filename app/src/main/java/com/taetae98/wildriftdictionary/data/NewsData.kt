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

    val specialNews by lazy {
        try {
            ArrayList<News>().apply {
                document.getElementsByClass("newsCard-1JDzV").forEach {
                    val imageURL = it.attr("style").replaceBefore("http", "").replaceAfter("jpg", "")
                    val title = try { it.getElementsByClass("title-2S683").first().text() } catch (e: Exception) { e.printStackTrace(); "" }
                    val description = try { it.getElementsByClass("category-1T7h7").first().text() } catch (e: Exception) { e.printStackTrace(); "" }

                    add(News(imageURL, title, description, type = News.Type.SPECIAL))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}