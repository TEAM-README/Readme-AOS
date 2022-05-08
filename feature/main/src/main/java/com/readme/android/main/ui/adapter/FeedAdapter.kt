package com.readme.android.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.core.util.ItemDiffCallback
import com.readme.android.domain.entity.Feed
import com.readme.android.main.databinding.ItemFeedBinding

class FeedAdapter : ListAdapter<Feed, FeedAdapter.FeedViewHolder>(
    ItemDiffCallback<Feed>(
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
                root.layoutParams = LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 18, 0)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class FeedViewHolder(private val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(feed: Feed) {
            binding.feedData = feed
        }
    }
}
