package com.taetae98.wildriftdictionary.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class RuneData private constructor() {
    companion object {
        private var instance: RuneData? = null

        fun getInstance(): RuneData {
            return instance ?: synchronized(this) {
                instance ?: RuneData().also {
                    instance = it
                }
            }
        }
    }

    private val document by lazy { runBlocking(Dispatchers.IO) { try { Jsoup.connect("https://poro.gg/wildrift/runes").get() } catch (e: Exception) { e.printStackTrace(); Jsoup.parse("") } } }

    val runes: List<Rune> by lazy {
        try {
            ArrayList<Rune>().apply {
                document.getElementsByClass("wildrift-runes__group").forEach {
                    val type = try {
                        when (it.getElementsByClass("wildrift-runes__group__header").first().text()) {
                            "핵심", "KeyStone" -> {
                                Rune.Type.KEY_STONE
                            }
                            "지배", "Resolve" -> {
                                Rune.Type.RESOLVE
                            }
                            "결의", "Domination" -> {
                                Rune.Type.DOMINATION
                            }
                            "영감", "Inspiration" -> {
                                Rune.Type.INSPIRATION
                            }
                            else -> {
                                throw IllegalStateException()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Rune.Type.NONE
                    }

                    try {
                        it.getElementsByClass("wildrift-runes__group__content").first().child(0).children().forEach { runeElement ->
                            val imageURL = try {
                                runeElement.child(0).child(0).attr("src")
                            } catch (e: Exception) {
                                e.printStackTrace()
                                ""
                            }
                            val name = try {
                                runeElement.child(0).child(1).text()
                            } catch (e: Exception) {
                                e.printStackTrace()
                                ""
                            }

                            val ability = try {
                                runeElement.child(1).text()
                            } catch (e: Exception) {
                                e.printStackTrace()
                                ""
                            }

                            val description = try {
                                runeElement.child(2).text()
                            } catch (e: Exception) {
                                e.printStackTrace()
                                ""
                            }

                            add(Rune(imageURL, name, type, ability, description).also { rune -> Log.d("PASS", rune.toString()) })
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}