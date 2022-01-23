package com.revolution.bookexpert.ui.history

import codeninjas.musicakinator.other.base.BaseView
import com.revolution.bookexpert.ui.history.adapter.HistoryItem
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface HistoryView : BaseView {
    fun onHistoryLoaded(items: List<HistoryItem>)
    fun onItemDeleted(position: Int)
}