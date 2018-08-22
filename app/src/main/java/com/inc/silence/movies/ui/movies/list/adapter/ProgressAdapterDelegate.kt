package com.inc.silence.movies.ui.movies.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.inc.silence.movies.R
import com.inc.silence.movies.utils.extension.inflate

class ProgressAdapterDelegate : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            ProgressViewHolder(parent.inflate(R.layout.item_progress))

    override fun isForViewType(items: MutableList<Any>, position: Int) =
            items[position] is ProgressItem

    override fun onBindViewHolder(items: MutableList<Any>,
                                  position: Int,
                                  holder: RecyclerView.ViewHolder,
                                  payloads: MutableList<Any>) {}

    private class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view)
}