package com.readme.android.main.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.main.databinding.LayoutFeedHeaderBinding

class HomeHeaderAdapter(
    private val isCategorySelected: Boolean,
    private val selectedCategory: String? = null,
    private val onCategoryIconClick: () -> Unit
) : RecyclerView.Adapter<HomeHeaderAdapter.HomeHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutFeedHeaderBinding.inflate(inflater, parent, false)
        return HomeHeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHeaderViewHolder, position: Int) {
        holder.onBind(isCategorySelected, selectedCategory, onCategoryIconClick)
    }

    class HomeHeaderViewHolder(private val binding: LayoutFeedHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            isCategorySelected: Boolean,
            selectedCategory: String?,
            onCategoryIconClick: () -> Unit
        ) {
            binding.isCategorySelected = isCategorySelected
            if (isCategorySelected) binding.selectedCategory = selectedCategory
            binding.ivCategory.setOnClickListener { onCategoryIconClick() }
        }
    }

    override fun getItemCount() = 1
}
