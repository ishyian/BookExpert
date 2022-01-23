package com.revolution.bookexpert.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.revolution.bookexpert.R
import com.revolution.bookexpert.databinding.MainFragmentBinding
import com.revolution.bookexpert.other.base.BaseFragment
import com.revolution.bookexpert.other.custom.annotations.GlobalRouterQualifier
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainFragment : BaseFragment(), MainView {

    override val layoutRes: Int
        get() = R.layout.main_fragment

    private val viewBinding: MainFragmentBinding by viewBinding()

    @Inject
    @GlobalRouterQualifier
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        viewBinding.layoutStart.setOnClickListener {
            presenter.onStartClicked()
        }
        viewBinding.layoutHistory.setOnClickListener {
            presenter.onHistoryClicked()
        }
    }

    companion object {
        fun toScreen(): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment {
                    return MainFragment()
                }
            }
        }
    }
}
