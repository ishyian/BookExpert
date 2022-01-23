package com.revolution.bookexpert.ui.main

import com.revolution.bookexpert.other.base.BasePresenter
import com.revolution.bookexpert.other.custom.annotations.GlobalRouterQualifier
import com.revolution.bookexpert.ui.chat.BookExpertChatFragment
import com.revolution.bookexpert.ui.history.HistoryFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter
@Inject
constructor(
    @GlobalRouterQualifier private val router: Router
) : BasePresenter<MainView>() {

    fun onHistoryClicked() {
        router.navigateTo(HistoryFragment.toScreen())
    }

    fun onStartClicked() {
        router.navigateTo(BookExpertChatFragment.toScreen())
    }
}