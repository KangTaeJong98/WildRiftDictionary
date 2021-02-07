package com.taetae98.wildriftdictionary.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.Serializable

data class Champion(
    val nameLocale: String = "",
    val nameEn: String = "",
    val imageURL: String = "",
    val headerImageURL: String = "",
    val splashImageURL: String = "",
    val informationURL: String = "",
    val line: List<Line> = emptyList(),
) : Serializable {
    private val document by lazy { runBlocking(Dispatchers.IO) { try { Jsoup.connect(informationURL).get() } catch (e: Exception) { e.printStackTrace(); Jsoup.parse("") } } }

    val cost by lazy { "" }
    val wildCore by lazy { "" }
    val ability: List<Ability> by lazy {
        try {
            ArrayList<Ability>().apply {
                document.getElementsByClass("wildrift-detail__stats1").first().children().forEach {
                    add(Ability(it.child(0).text(), it.child(1).text(), Ability.Type.NORMAL).also { ability ->
                        Log.d("PASS", ability.toString())
                    })
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    val subAbility: List<Ability> by lazy {
        try {
            ArrayList<Ability>().apply {
                document.getElementsByClass("wildrift-detail__stats2").first().children().forEach {
                    val key = try { it.child(0).text() } catch (e: Exception) { e.printStackTrace(); "" }
                    val value = try { it.child(1).text() } catch (e: Exception) { e.printStackTrace(); "" }

                    add(Ability(key, value, Ability.Type.SUB).also { ability ->
                        Log.d("PASS", ability.toString())
                    })
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    val skill: List<Skill> by lazy {
        try {
            ArrayList<Skill>().apply {
                document.getElementsByClass("wildrift-detail__skills__info").forEach {
                    val imageURL = try {
                        val url = it.getElementsByClass("wildrift-detail__skills__info__image").first().child(0).attr("src")
                        "http:$url"
                    } catch (e: Exception) {
                        e.printStackTrace()
                        ""
                    }

                    val infoElement = try {
                        it.getElementsByClass("wildrift-detail__skills__info__content").first()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Element("")
                    }

                    val name = try {
                        infoElement.child(0).text()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        ""
                    }

                    val dataElement = try {
                        infoElement.child(1)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Element("")
                    }

                    val data = dataElement.toString()
                    val st = dataElement.text().split(" ")

                    val time = st.first()

                    val costType = when {
                        data.contains("<i class=\"fas fa-tint\" style=\"margin-left: 6px; color: #207ac7\"></i>") -> {
                            Skill.CostType.MP
                        }
                        data.contains("<i class=\"fas fa-heart\" style=\"margin-left: 6px; color: #11b288\"></i>") -> {
                            Skill.CostType.HP
                        }
                        data.contains("<i class=\"fas fa-bolt\" style=\"margin-left: 6px; color: #ffc528\"></i>") -> {
                            Skill.CostType.ENERGY
                        }
                        else -> {
                            Skill.CostType.NONE
                        }
                    }

                    val cost = if (costType == Skill.CostType.NONE) {
                        ""
                    } else {
                        st.last()
                    }

                    val description = infoElement.child(2).text()
                    add(Skill(imageURL, name, time, costType, cost, description).also { skill -> Log.d("PASS", skill.toString()) })
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    val item by lazy {
        try {
            ArrayList<Item>().apply {
                document.getElementsByClass("wildrift-detail__recommend__item__content")[0].children().forEach {
                    ItemData.getInstance().items.find { item ->
                        item.name == it.attr("alt")
                    }?.let { item ->
                        add(item)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    val rune: List<Rune> by lazy {
        try {
            ArrayList<Rune>().apply {
                document.getElementsByClass("wildrift-detail__recommend__item__content")[1].children().forEach {
                    RuneData.getInstance().runes.find { rune ->
                        rune.name == it.attr("alt")
                    }?.let { rune ->
                        add(rune)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    val spell: List<Spell> by lazy {
        try {
            ArrayList<Spell>().apply {
                document.getElementsByClass("wildrift-detail__recommend__item__content")[2].children().forEach {
                    Jsoup.parse(it.attr("title")).also { element ->
                        val imageURL = try {
                            element.getElementsByClass("wdr-tooltip__item__image").first().child(0).attr("src").trim()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            ""
                        }

                        val informationElement = element.getElementsByClass("wdr-tooltip__item__info")

                        val name = try {
                            informationElement.first().child(0).text().trim()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            ""
                        }
                        val time = try {
                            informationElement.first().child(1).text().split(" ").last().trim()
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                            ""
                        }
                        val description = try {
                            element.getElementsByClass("wdr-tooltip__description").first().text().trim()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            ""
                        }

                        add(Spell(imageURL, name, time, description).also { spell ->
                            Log.d("PASS", spell.toString())
                        })
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    enum class Line {
        NONE, TOP, JUNGLE, MID, BOT, SUPPORTER
    }

    data class Ability(
            var key: String = "",
            var value: String = "",
            var type: Type = Type.NORMAL
    ) {
        enum class Type {
            NORMAL, SUB
        }
    }
}