package com.revolution.bookexpert.bot

import com.revolution.bookexpert.data.User
import java.util.UUID

fun getBookExpertUser() = User(
    "1",
    "БукЕксперт",
    "https://i.imgur.com/rBR4tOQ.png",
    isOnline = true
)

fun getRealUser() = User(
    "0",
    "Я",
    "",
    isOnline = true
)

fun getRandomId(): String {
    return UUID.randomUUID().leastSignificantBits.toString()
}