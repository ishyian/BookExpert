package com.revolution.bookexpert.data

import androidx.annotation.StringRes

data class Book(
    val title: String,
    val author: String,
    @StringRes val description: Int,
    val image: String,
    val type: BookType
)

