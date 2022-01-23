package com.revolution.bookexpert.bot

import android.content.Context
import com.revolution.bookexpert.R
import com.revolution.bookexpert.data.BookType
import com.revolution.bookexpert.data.Library
import com.revolution.bookexpert.data.Message
import com.revolution.bookexpert.other.TinyDB
import java.util.Date

class BookExpertBot(val context: Context) {
    private lateinit var userMessage: String
    private lateinit var botMessage: String

    private var messageState: Int = 0

    private var bookExpertBotReply: BookExpertBotReply? = null

    private var lastMessageAskForHelp = false

    private val tinyDb by lazy {
        TinyDB(context)
    }

    fun setBookExpertBotReply(container: BookExpertBotReply) {
        bookExpertBotReply = container
    }

    fun read(msg: String) {
        userMessage = msg
        userMessage = userMessage.lowercase().trim()
    }

    private fun write(message: String, scroll: Boolean = true) {
        ++messageState
        botMessage = message
        val botReply = Message(getRandomId(), getBookExpertUser(), botMessage, Date())
        bookExpertBotReply?.onBotReplied(botReply, scroll)
    }

    private fun writeImageMessage(image: String) {
        ++messageState
        val botReply = Message(getRandomId(), getBookExpertUser(), "", Date(), Message.Image(image))
        bookExpertBotReply?.onBotReplied(botReply, false)
    }

    private fun writeSearchMessage(message: String) {
        ++messageState
        val botReply =
            Message(getRandomId(), getBookExpertUser(), "", Date(), search = Message.Search(message))
        bookExpertBotReply?.onBotReplied(botReply, false)
    }

    fun handle() = when (messageState) {
        0 -> write(greetings.random())
        1 -> if (!isMessageContainsOneOfTheWords(userMessage, greetingsWords)) {
            write(context.getString(R.string.no_greetings))
        } else {
            write(context.getString(R.string.glad_to_see_you))
            write(
                "Дайте вгадаю, навіщо ви тут - напевне за книгою? Напишіть \"Так\" або \"Давай\", якщо є таке бажання!"
            )
        }
        else -> when {
            lastMessageAskForHelp -> {
                val bookType = getBookType(userMessage)
                sendRandomBookFromType(bookType)
                lastMessageAskForHelp = false
            }
            isMessageContainsOneOfTheWords(userMessage, askWords) || isMessageContainsOneOfTheWords(
                userMessage,
                yesWords
            ) -> {
                write(
                    "У цьому я професіонал! Можу порекомендовати книгу з цих тем:\n- прокрастинація;\n- проблеми у комунікації;\n" +
                        "- тривожність;\n" +
                        "- відсутність турботи про себе;\n" +
                        "- відсутність ворк-лайф балансу;\n- інші.\nЩо Вас цікавить?"
                )
                lastMessageAskForHelp = true
            }
            isMessageContainsOneOfTheWords(userMessage, thanksWords) -> {
                write(
                    context.getString(R.string.please)
                )
            }
            else -> {
                write(
                    "На жаль, я Вас не зрозумів. Але все таки можу порекомендовати книгу. Напишіть \"Так\" або \"Давай\", якщо є таке бажання!"
                )
            }
        }
    }

    private fun isMessageContainsOneOfTheWords(message: String, words: List<String>): Boolean {
        words.forEach {
            if (message.contains(it)) return true
        }
        return false
    }

    private fun sendRandomBookFromType(bookType: BookType) {
        val randomBook = Library.books.filter { it.type == bookType }.random()
        val historyItems = tinyDb.getListHistory("history_items")
        historyItems.add(randomBook)
        tinyDb.putHistoryList("history_items", historyItems)
        write("Ми знайшли підходящу книгу! Це книга \"${randomBook.title}\" за авторством ${randomBook.author}", true)
        write(context.getString(randomBook.description), false)
        writeImageMessage(randomBook.image)
        writeSearchMessage(randomBook.title)
    }

    private fun getBookType(userMessage: String): BookType {
        return when {
            isMessageContainsOneOfTheWords(userMessage, anxietyWords) -> BookType.ANXIETY
            isMessageContainsOneOfTheWords(userMessage, procrastinationWords) -> BookType.PROCRASTINATION
            isMessageContainsOneOfTheWords(userMessage, communicationWords) -> BookType.COMMUNICATION
            isMessageContainsOneOfTheWords(userMessage, selfCareWords) -> BookType.SELF_CARE
            isMessageContainsOneOfTheWords(userMessage, workLifeWords) -> BookType.WORK_LIFE_BALANCE
            else -> BookType.OTHER
        }
    }
}

interface BookExpertBotReply {
    fun onBotReplied(message: Message, scroll: Boolean)
}