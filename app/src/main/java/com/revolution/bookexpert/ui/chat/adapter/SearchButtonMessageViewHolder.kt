package com.revolution.bookexpert.ui.chat.adapter

import android.view.View
import android.widget.TextView
import com.revolution.bookexpert.R
import com.revolution.bookexpert.data.Message
import com.stfalcon.chatkit.messages.MessageHolders.IncomingTextMessageViewHolder
import com.stfalcon.chatkit.utils.DateFormatter

class SearchButtonMessageViewHolder(itemView: View, payload: Any?) :
    IncomingTextMessageViewHolder<Message>(itemView, payload) {

    override fun onBind(message: Message) {
        super.onBind(message)
        val tvTime: TextView = itemView.findViewById(R.id.time)
        tvTime.text = DateFormatter.format(
            message.createdAt,
            DateFormatter.Template.TIME
        )
    }

}