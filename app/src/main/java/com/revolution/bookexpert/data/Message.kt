package com.revolution.bookexpert.data

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.MessageContentType
import java.util.Date

class Message(
    private val id: String,
    private val user: User,
    private var text: String?,
    private val createdAt: Date,
    private val image: Image? = null,
    val search: Search? = null
) : IMessage, MessageContentType.Image, MessageContentType {

    override fun getId() = id

    override fun getText() = text

    override fun getUser() = user

    override fun getCreatedAt() = createdAt

    override fun getImageUrl(): String? = image?.url

    class Image(val url: String)
    class Search(val query: String)
}

