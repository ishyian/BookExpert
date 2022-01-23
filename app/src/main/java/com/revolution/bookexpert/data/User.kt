package com.revolution.bookexpert.data

import com.stfalcon.chatkit.commons.models.IUser

class User(
    private val id: String,
    private val name: String,
    private val avatar: String,
    val isOnline: Boolean
) : IUser {

    override fun getId() = id

    override fun getName() = name

    override fun getAvatar() = avatar

}

