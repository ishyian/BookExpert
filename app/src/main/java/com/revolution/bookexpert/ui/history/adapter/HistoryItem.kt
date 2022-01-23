package com.revolution.bookexpert.ui.history.adapter

import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.revolution.bookexpert.R
import com.revolution.bookexpert.data.Book

class HistoryItem(val data: Book) :
    AbstractItem<HistoryItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_history
    override val type: Int
        get() = R.id.item_history

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    inner class ViewHolder(view: View) : FastAdapter.ViewHolder<HistoryItem>(view) {
        override fun bindView(item: HistoryItem, payloads: MutableList<Any>) {
            val imagePhoto = itemView.findViewById<ShapeableImageView>(R.id.imageBook)
            val radius = itemView.context.resources.getDimension(R.dimen.corner_radius_20)
            val shapeAppearanceModel = imagePhoto.shapeAppearanceModel.toBuilder()
                .setAllCornerSizes(radius)
                .build()

            itemView.findViewById<TextView>(R.id.textBookTitle).text = "${item.data.title}\n${item.data.author}"
            imagePhoto.shapeAppearanceModel = shapeAppearanceModel
            Glide.with(itemView.context).load(item.data.image)
                .into(imagePhoto)
        }

        override fun unbindView(item: HistoryItem) {

        }
    }
}