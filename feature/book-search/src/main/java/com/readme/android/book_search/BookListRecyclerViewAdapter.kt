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
import com.readme.android.domain.entity.BookInfo


class BookListRecyclerViewAdapter :
    ListAdapter<BookInfo, BookListRecyclerViewAdapter.BookListRecyclerViewHolder>(bookInfoDiffUtil) {


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
            binding.bookTitleProcessed = Html.fromHtml(data.title,Html.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)
        }
    }

    companion object {
        private val bookInfoDiffUtil = object : DiffUtil.ItemCallback<BookInfo>() {
            override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean =
                oldItem == newItem
        }
    }
}
