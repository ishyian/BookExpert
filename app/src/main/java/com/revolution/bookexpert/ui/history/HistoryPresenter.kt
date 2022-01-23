package com.revolution.bookexpert.ui.history

import com.revolution.bookexpert.data.Book
import com.revolution.bookexpert.other.TinyDB
import com.revolution.bookexpert.other.base.BasePresenter
import com.revolution.bookexpert.other.custom.annotations.GlobalRouterQualifier
import com.revolution.bookexpert.ui.history.adapter.HistoryItem
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class HistoryPresenter
@Inject
constructor(
    @GlobalRouterQualifier private val router: Router,
    private val tinyDB: TinyDB
) : BasePresenter<HistoryView>() {

    fun onCloseClick() {
        router.exit()
    }

    fun loadHistory() {
        val historyItems = tinyDB.getListHistory("history_items")
        viewState.onHistoryLoaded(historyItems.reversed().map { HistoryItem(it) })
    }

    fun deleteHistoryItem(position: Int, data: Book) {
        val historyItems = tinyDB.getListHistory("history_items")
        historyItems.remove(data)
        tinyDB.putHistoryList("history_items", historyItems)
        viewState.onItemDeleted(position)
    }
}
