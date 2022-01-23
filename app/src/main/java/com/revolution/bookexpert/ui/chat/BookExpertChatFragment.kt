package com.revolution.bookexpert.ui.chat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.revolution.bookexpert.R
import com.revolution.bookexpert.bot.BookExpertBot
import com.revolution.bookexpert.bot.BookExpertBotReply
import com.revolution.bookexpert.bot.getRandomId
import com.revolution.bookexpert.bot.getRealUser
import com.revolution.bookexpert.data.Message
import com.revolution.bookexpert.databinding.BookExpertChatFragmentBinding
import com.revolution.bookexpert.other.base.BaseFragment
import com.revolution.bookexpert.other.base.OnSpeechRecognizeListener
import com.revolution.bookexpert.other.custom.extensions.openSearchQuery
import com.revolution.bookexpert.ui.MainActivity
import com.revolution.bookexpert.ui.chat.adapter.SearchButtonMessageViewHolder
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.messages.MessageHolders.ContentChecker
import com.stfalcon.chatkit.messages.MessageInput.AttachmentsListener
import com.stfalcon.chatkit.messages.MessageInput.InputListener
import com.stfalcon.chatkit.messages.MessagesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppScreen
import java.util.Date
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class BookExpertChatFragment : BaseFragment(),
    BookExpertChatView,
    InputListener,
    AttachmentsListener,
    ContentChecker<Message>,
    MessagesListAdapter.OnLoadMoreListener,
    BookExpertBotReply,
    OnSpeechRecognizeListener {

    override val layoutRes: Int
        get() = R.layout.book_expert_chat_fragment

    private val viewBinding: BookExpertChatFragmentBinding by viewBinding()

    private val imageLoader: ImageLoader by lazy {
        ImageLoader { imageView: ImageView, url: String?, payload: Any? ->
            Glide.with(requireContext()).load(url).into(imageView)
        }
    }

    @Inject
    lateinit var presenterProvider: Provider<BookExpertChatPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private var messagesAdapter: MessagesListAdapter<Message>? = null

    private val expertBot by lazy {
        BookExpertBot(requireContext())
    }

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        initAdapter()

        with(viewBinding.input) {
            setInputListener(this@BookExpertChatFragment)
            setAttachmentsListener(this@BookExpertChatFragment)
        }

        viewBinding.toolbar.setNavigationOnClickListener {
            presenter.onCloseClick()
        }

        expertBot.setBookExpertBotReply(this)
        expertBot.handle()
    }

    override fun onSubmit(input: CharSequence): Boolean {
        val message = Message(getRandomId(), getRealUser(), input.toString(), Date())
        messagesAdapter?.addToStart(
            message,
            true
        )
        expertBot.read(message.text.toString())
        expertBot.handle()
        return true
    }

    override fun onAddAttachments() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "uk-UA")
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        requireActivity().startActivityForResult(
            intent,
            MainActivity.REQUEST_CODE_SPEECH_RECOGNIZER
        )
    }

    override fun hasContentFor(message: Message, type: Byte): Boolean {
        return if (type == CONTENT_TYPE_VOICE) {
            message.search?.query != null
        } else false
    }

    private fun initAdapter() {
        val holders = MessageHolders()
            .registerContentType(
                CONTENT_TYPE_VOICE,
                SearchButtonMessageViewHolder::class.java,
                R.layout.item_search_message,
                SearchButtonMessageViewHolder::class.java,
                R.layout.item_search_message,
                this
            )

        messagesAdapter = MessagesListAdapter(Companion.senderId, holders, imageLoader)
        messagesAdapter?.setLoadMoreListener(this)
        messagesAdapter?.setOnMessageClickListener {
            if (it.search != null) {
                openSearchQuery("${it.search}")
            }
        }
        viewBinding.messagesList.setAdapter(messagesAdapter)
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

    override fun onSpeechRecognize(speech: List<String>) {
        speech.firstOrNull()?.let {
            viewBinding.input.inputEditText.setText(it)
        }
    }

    companion object {
        private const val CONTENT_TYPE_VOICE: Byte = 1
        private const val senderId = "0"

        fun toScreen(): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment {
                    return BookExpertChatFragment()
                }
            }
        }
    }
}