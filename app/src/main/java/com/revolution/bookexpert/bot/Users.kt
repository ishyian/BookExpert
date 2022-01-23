package com.revolution.bookexpert.bot

import com.revolution.bookexpert.data.User
import java.util.UUID

fun getBookExpertUser() = User(
    "1",
    "БукЕксперт",
    "http://i.imgur.com/pv1tBmT.png",
    isOnline = true
)

fun getRealUser() = User(
    "0",
    "Я",
    "http://i.imgur.com/pv1tBmT.png",
    isOnline = true
)

fun getRandomId(): String {
    return UUID.randomUUID().leastSignificantBits.toString()
}