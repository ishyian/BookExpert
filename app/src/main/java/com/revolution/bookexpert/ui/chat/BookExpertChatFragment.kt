package com.revolution.bookexpert.ui.chat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.revolution.bookexpert.R
import com.revolution.bookexpert.bot.BookExpertBot
import com.revolution.bookexpert.bot.BookExpertBotReply
import com.revolution.bookexpert.bot.getRandomId
import com.revolution.bookexpert.bot.getRealUser
import com.revolution.bookexpert.data.Message
import com.revolution.bookexpert.other.base.BaseFragment
import com.revolution.bookexpert.ui.chat.adapter.IncomingVoiceMessageViewHolder
import com.revolution.bookexpert.ui.chat.adapter.OutcomingVoiceMessageViewHolder
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.messages.MessageHolders.ContentChecker
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessageInput.AttachmentsListener
import com.stfalcon.chatkit.messages.MessageInput.InputListener
import com.stfalcon.chatkit.messages.MessagesList
import com.stfalcon.chatkit.messages.MessagesListAdapter
import ru.terrakok.cicerone.android.support.SupportAppScreen
import java.util.Date
import java.util.Locale
import java.util.UUID

class BookExpertChatFragment : BaseFragment(), InputListener,
    AttachmentsListener,
    ContentChecker<Message>,
    MessagesListAdapter.OnLoadMoreListener,
    BookExpertBotReply {

    override val layoutRes: Int
        get() = R.layout.book_expert_chat_fragment

    private var messagesList: MessagesList? = null

    private val senderId = "0"
    private var imageLoader: ImageLoader? = null
    private var messagesAdapter: MessagesListAdapter<Message>? = null

    private val expertBot by lazy {
        BookExpertBot(requireContext())
    }

    private var tts: TextToSpeech? = null
    var isTtsInit = false

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        tts = TextToSpeech(requireContext()) {
            val result = tts?.setLanguage(Locale.forLanguageTag("uk-UA"))

            if (result == TextToSpeech.SUCCESS) {
            }

            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                isTtsInit = true
            } else {

            }
        }

        imageLoader = ImageLoader { imageView: ImageView, url: String?, payload: Any? ->
            Glide.with(requireContext()).load(url).into(imageView)
        }
        messagesList = view.findViewById(R.id.messagesList)
        initAdapter()
        val input: MessageInput = view.findViewById(R.id.input)
        input.setInputListener(this)
        input.setAttachmentsListener(this)
        expertBot.setBookExpertBotReply(this)
    }

    override fun onSubmit(input: CharSequence): Boolean {
        val message = Message(getRandomId(), getRealUser(), input.toString(), Date())
        messagesAdapter?.addToStart(
            message,
            true
        )
        expertBot.read(message.text ?: "")
        expertBot.handle()
        return true
    }

    override fun onAddAttachments() {

    }

    override fun onStart() {
        super.onStart()
        expertBot.handle()
    }

    override fun hasContentFor(message: Message, type: Byte): Boolean {
        return if (type == CONTENT_TYPE_VOICE) {
            message.voice?.description != null && message.voice.description.isNotEmpty()
        } else false
    }

    private fun initAdapter() {
        val holders = MessageHolders()
            .registerContentType(
                CONTENT_TYPE_VOICE,
                IncomingVoiceMessageViewHolder::class.java,
                R.layout.item_custom_incoming_voice_message,
                OutcomingVoiceMessageViewHolder::class.java,
                R.layout.item_custom_outcoming_voice_message,
                this
            )

        messagesAdapter = MessagesListAdapter(senderId, holders, imageLoader)
        messagesAdapter?.setLoadMoreListener(this)
        messagesAdapter?.setOnMessageClickListener {
            if (it.voice != null) {
                speak(it.voice.description)
            }
        }
        messagesList!!.setAdapter(messagesAdapter)
    }

    override fun onLoadMore(page: Int, totalItemsCount: Int) {
        // Nothing for now
    }

    override fun onBotReplied(message: Message) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                messagesAdapter?.addToStart(message, true)
            }, 1000
        )
    }

    private fun speak(text: String) {
        if (tts != null) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, UUID.randomUUID().toString())
        } else {
            Log.d("TTS", "tts is null")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
        }
    }

    companion object {
        private const val CONTENT_TYPE_VOICE: Byte = 1

        fun toScreen(): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment {
                    return BookExpertChatFragment()
                }
            }
        }
    }
}