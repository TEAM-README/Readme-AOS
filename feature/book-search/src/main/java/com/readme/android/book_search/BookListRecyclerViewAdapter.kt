package com.readme.android.book_search

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.book_search.databinding.ItemBookSearchBinding
import com.readme.android.core.util.ItemDiffCallback
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.entity.Feed


class BookListRecyclerViewAdapter :
    ListAdapter<BookInfo, BookListRecyclerViewAdapter.BookListRecyclerViewHolder>(
        ItemDiffCallback<BookInfo>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.title == new.title }
        )
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListRecyclerViewHolder {
        val binding =
            ItemBookSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListRecyclerViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: BookListRecyclerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class BookListRecyclerViewHolder(val binding: ItemBookSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun onBind(data: BookInfo){
            binding.bookInfo = data
        }
    }
}
