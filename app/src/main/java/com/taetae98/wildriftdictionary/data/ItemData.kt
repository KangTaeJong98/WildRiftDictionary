package com.taetae98.wildriftdictionary.data

import android.util.Log
import com.taetae98.wildriftdictionary.singleton.LocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class ItemData private constructor() {
    companion object {
        private var instance: ItemData? = null

        fun getInstance(): ItemData {
            return instance ?: synchronized(this) {
                instance ?: ItemData().also {
                    instance = it
                }
            }
        }
    }

    private val document by lazy { runBlocking(Dispatchers.IO) { try { Jsoup.connect("https://poro.gg/wildrift/items?hl=${LocaleManager.getPoroGGLocale()}").get() } catch (e: Exception) { e.printStackTrace(); Jsoup.parse("") } } }

    val items: List<Item> by lazy {
        try {
            ArrayList<Item>().apply {
                document.getElementsByClass("mb-3").forEach {
                    val type = try {
                        when (it.getElementsByClass("wildrift-items__group").first().text()) {
                            "물리 아이템", "Physical Items" -> {
                                Item.Type.PHYSICAL
                            }
                            "마법 아이템", "Magic Items" -> {
                                Item.Type.MAGIC
                            }
                            "방어 아이템", "Defense Items" -> {
                                Item.Type.DEFENSE
                            }
                            "신발", "Boots Items" -> {
                                Item.Type.BOOTS
                            }
                            else -> {
                                throw IllegalStateException()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Item.Type.NONE
                    }

                    it.getElementsByClass("wildrift-items__box").forEach { element ->
                        val level = try {
                            when (element.getElementsByClass("wildrift-items__box__header").first().text()) {
                                "최고 단계", "Upgraded" -> {
                                    Item.Level.UPGRADED
                                }
                                "중간 단계", "Mid Tier" -> {
                                    Item.Level.MID
                                }
                                "기본", "Basic" -> {
                                    Item.Level.BASIC
                                }
                                "마법 부여", "Enchantments" -> {
                                    Item.Level.ENCHANTMENT
                                }
                                else -> {
                                    throw IllegalStateException()
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Item.Level.NONE
                        }

                        try {
                            element.getElementsByClass("wildrift-items__box__content").first().child(0).children().forEach { itemElement ->
                                val imageURL = try { itemElement.child(0).attr("src").trim() } catch (e: Exception) { e.printStackTrace(); "" }
                                val name = try { itemElement.child(0).attr("alt").trim() } catch (e: Exception) { e.printStackTrace(); "" }

                                val informationElement = try { Jsoup.parse(itemElement.child(0).attr("title")) } catch (e: Exception) { e.printStackTrace(); Jsoup.parse("") }

                                val cost = try { informationElement.getElementsByClass("wdr-tooltip__item__info").first().child(1).text().trim() } catch (e: Exception) { e.printStackTrace(); "" }
                                val ability = try { informationElement.getElementsByClass("wdr-tooltip__stats").text().trim() } catch (e: Exception) { e.printStackTrace(); "" }
                                val description = try { informationElement.getElementsByClass("wdr-tooltip__description").text().trim() } catch (e: Exception) { e.printStackTrace(); "" }

                                add(Item(imageURL, name, cost, ability, description, type, level).also { item -> Log.d("PASS", item.toString()) })
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}