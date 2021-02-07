package com.taetae98.wildriftdictionary.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class Champion(
    var nameKr: String = "",
    var nameEn: String = "",
    var imageURL: String = "",
    var headerImageURL: String = "",
    var splashImageURL: String = "",
    var informationURL: String = "",
    var line: List<Line> = emptyList(),
    var cost: String = "",
    var wildCore: String = "",
    var ability: List<Ability> = emptyList(),
    var subAbility: List<Ability> = emptyList(),
    var skill: List<Skill> = emptyList()
) : Serializable {
    enum class Line {
        NONE, TOP, JUNGLE, MID, BOT, SUPPORTER
    }

    private val document by lazy { runBlocking(Dispatchers.IO) { try { Jsoup.connect(informationURL).get() } catch (e: Exception) { e.printStackTrace(); Jsoup.parse("") } } }

    fun initAbility() {
        if (ability.isEmpty()) {
            ability = try {
                ArrayList<Ability>().apply {
                    document.getElementsByClass("wildrift-detail__stats1").first().children().forEach {
                        add(Ability(it.child(0).text(), it.child(1).text(), Ability.Type.NORMAL))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    fun initSubAbility() {
        if (subAbility.isEmpty()) {
            subAbility = try {
                ArrayList<Ability>().apply {
                    document.getElementsByClass("wildrift-detail__stats2").first().children().forEach {
                        val key = try { it.child(0).text() } catch (e: Exception) { e.printStackTrace(); "" }
                        val value = try { it.child(1).text() } catch (e: Exception) { e.printStackTrace(); "" }

                        add(Ability(key, value, Ability.Type.NORMAL))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    fun initSkill() {
        if (skill.isEmpty()) {
            skill = try {
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
                        val st = StringTokenizer(dataElement.text())

                        val time = st.nextToken()

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
                            st.nextToken()
                        }

                        val description = infoElement.child(2).text()
                        add(Skill(imageURL, name, time, costType, cost, description).also { champion -> Log.d("PASS", champion.toString()) })
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}