package com.revolution.bookexpert.ui.chat

import com.revolution.bookexpert.other.base.BasePresenter
import com.revolution.bookexpert.other.custom.annotations.GlobalRouterQualifier
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class BookExpertChatPresenter
@Inject
constructor(
    @GlobalRouterQualifier private val router: Router
) : BasePresenter<BookExpertChatView>() {

    fun onCloseClick() {
        router.exit()
    }
}
