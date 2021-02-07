package com.taetae98.wildriftdictionary.singleton

import java.util.*

object LocaleManager {
    private fun getLocale(): Locale {
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

    fun getLoLLocale(): String {
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

    fun getWildRiftLocale(): String {
        return when(getLocale()) {
            Locale.KOREA -> {
                "ko-kr"
            }
            Locale.US -> {
                "en-us"
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }
}