package codeninjas.musicakinator.other.base

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showProgress()

    @StateStrategyType(SkipStrategy::class)
    fun hideProgress()

    @StateStrategyType(SkipStrategy::class)
    fun showError(throwable: Throwable, action: (() -> Unit?)? = null)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: String)

}