package com.revolution.bookexpert.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.revolution.bookexpert.R
import com.revolution.bookexpert.databinding.HistoryFragmentBinding
import com.revolution.bookexpert.other.base.BaseFragment
import com.revolution.bookexpert.other.custom.annotations.GlobalRouterQualifier
import com.revolution.bookexpert.other.custom.extensions.openSearchQuery
import com.revolution.bookexpert.ui.history.adapter.HistoryItem
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class HistoryFragment : BaseFragment(), HistoryView {

    override val layoutRes: Int
        get() = R.layout.history_fragment

    private val binding: HistoryFragmentBinding by viewBinding()

    private lateinit var itemAdapter: ItemAdapter<HistoryItem>
    private lateinit var adapter: FastAdapter<HistoryItem>

    @Inject
    @GlobalRouterQualifier
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: Provider<HistoryPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() {
        itemAdapter = ItemAdapter()
        adapter = FastAdapter.with(itemAdapter)

        adapter.addEventHook(object : ClickEventHook<HistoryItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if (viewHolder is HistoryItem.ViewHolder) {
                    viewHolder.itemView.findViewById(R.id.btnSearch)
                } else null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<HistoryItem>,
                item: HistoryItem
            ) {
                openSearchQuery("${item.data.title}")
            }
        })

        adapter.addEventHook(object : ClickEventHook<HistoryItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if (viewHolder is HistoryItem.ViewHolder) {
                    viewHolder.itemView.findViewById(R.id.imageDelete)
                } else null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<HistoryItem>,
                item: HistoryItem
            ) {
                presenter.deleteHistoryItem(position, item.data)
            }
        })

        binding.rvHistory.adapter = adapter

        binding.btnClose.setOnClickListener {
            presenter.onCloseClick()
        }

        presenter.loadHistory()
    }

    override fun onHistoryLoaded(items: List<HistoryItem>) {
        itemAdapter.clear()
        itemAdapter.add(items)
    }

    override fun onItemDeleted(position: Int) {
        itemAdapter.remove(position)
    }

    companion object {
        fun toScreen() =
            object : SupportAppScreen() {
                override fun getFragment(): Fragment {
                    return HistoryFragment()
                }
            }
    }
}