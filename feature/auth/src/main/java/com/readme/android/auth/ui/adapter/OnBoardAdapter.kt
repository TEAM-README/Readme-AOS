package com.readme.android.auth.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.auth.databinding.LayoutOnboardHolderBinding

class OnBoardAdapter : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding = LayoutOnboardHolderBinding.inflate(inflater, parent, false)
        return OnBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = 3

    class OnBoardViewHolder(
        private val binding: LayoutOnboardHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.onboardNum = position
        }
    }
}
