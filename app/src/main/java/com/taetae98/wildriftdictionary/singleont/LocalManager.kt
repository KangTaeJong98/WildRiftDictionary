package com.taetae98.wildriftdictionary.singleont

import java.util.*

object LocalManager {
    fun getLocale(): Locale {
        return when(Locale.getDefault()) {
            Locale.KOREAN, Locale.KOREA -> {
                Locale.KOREA
            }
            else -> {
                Locale.US
            }
        }
    }

    fun getPoroGGLocale(): String {
        return when(getLocale()) {
            Locale.KOREA -> {
                "ko-KR"
            }
            Locale.US -> {
                "en-US"
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun getRiotLocale(): String {
        return when(getLocale()) {
            Locale.KOREA -> {
                "ko_KR"
            }
            Locale.US -> {
                "en_US"
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }
}