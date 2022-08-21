package com.readme.android.main.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.main.databinding.LayoutFeedHeaderBinding

class HomeHeaderAdapter(
    private var isCategorySelected: Boolean,
    private var selectedCategory: String,
    private val onCategoryIconClick: () -> Unit
) : RecyclerView.Adapter<HomeHeaderAdapter.HomeHeaderViewHolder>() {

    fun refreshCategoryData(isCategorySelected: Boolean, selectedCategory: String) {
        this.isCategorySelected = isCategorySelected
        this.selectedCategory = selectedCategory
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutFeedHeaderBinding.inflate(inflater, parent, false)
        return HomeHeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHeaderViewHolder, position: Int) {
        holder.onBind(
            isCategorySelected = isCategorySelected,
            selectedCategory = selectedCategory,
            onCategoryIconClick = onCategoryIconClick
        )
    }

    class HomeHeaderViewHolder(private val binding: LayoutFeedHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            isCategorySelected: Boolean,
            selectedCategory: String,
            onCategoryIconClick: () -> Unit
        ) {
            binding.isCategorySelected = isCategorySelected
            binding.selectedCategory = selectedCategory
            binding.ivCategory.setOnClickListener { onCategoryIconClick() }
        }
    }

    override fun getItemCount() = 1
}
