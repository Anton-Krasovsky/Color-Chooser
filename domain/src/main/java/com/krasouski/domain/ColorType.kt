package com.krasouski.domain

enum class ColorType(val displayName: String) {
    NONE("None"),
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue");

    companion object {
        fun fromString(value: String): ColorType =
            entries.find { it.name == value } ?: NONE
    }
}