package com.readme.android.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.core_ui.ext.getDimen
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.core_ui.util.ItemDiffCallback
import com.readme.android.domain.entity.FeedInfo
import com.readme.android.main.databinding.ItemFeedBinding
import com.readme.android.shared.R

class FeedAdapter(
    private val onMoreClick: (Boolean) -> Unit,
    private val onFeedClick: (Int) -> Unit
) : ListAdapter<FeedInfo, FeedAdapter.FeedViewHolder>(
    ItemDiffCallback<FeedInfo>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.id == new.id }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding = ItemFeedBinding.inflate(inflater, parent, false)
        return FeedViewHolder(
            binding.apply {
                root.apply {
                    layoutParams = LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(
                            0, 0, getDimen(R.dimen.feed_margin), 0
                        )
                    }
                }
            }
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(getItem(position), onMoreClick, onFeedClick)
    }

    class FeedViewHolder(private val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(feedInfo: FeedInfo, onMoreClick: (Boolean) -> Unit, onFeedClick: (Int) -> Unit) {
            binding.feedData = feedInfo
            binding.btnMore.setOnSingleClickListener { onMoreClick(feedInfo.isMyFeed) }
            binding.root.setOnSingleClickListener { onFeedClick(feedInfo.id) }
        }
    }
}
