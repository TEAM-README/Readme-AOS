package com.readme.android.book_search

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.book_search.databinding.ItemBookSearchBinding
import com.readme.android.core_ui.util.ItemDiffCallback
import com.readme.android.domain.entity.BookInfo
import com.readme.android.navigator.MainNavigator


class BookListRecyclerViewAdapter(
    private val mainNavigator: MainNavigator,
    private val finishSearchActivity: (() -> Unit)
) :
    ListAdapter<BookInfo, BookListRecyclerViewAdapter.BookListRecyclerViewHolder>(
        ItemDiffCallback<BookInfo>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.title == new.title }
        )
    ) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListRecyclerViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding = ItemBookSearchBinding.inflate(inflater, parent, false)

        return BookListRecyclerViewHolder(binding, parent)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: BookListRecyclerViewHolder, position: Int) {
        holder.onBind(getItem(position), mainNavigator, finishSearchActivity)
    }

    class BookListRecyclerViewHolder(
        private val binding: ItemBookSearchBinding,
        private val parent: ViewGroup
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun onBind(
            data: BookInfo,
            mainNavigator: MainNavigator,
            finishSearchActivity: (() -> Unit)
        ) {
            binding.bookInfo = data
            binding.root.setOnClickListener {
                mainNavigator.feedWriteLogin(
                    context = parent.context,
                    title = Pair("title", data.title),
                    author = Pair("author", data.author ?: ""),
                    image = Pair("image", data.image),
                    isbn = Pair("isbn", data.isbn),
                    subIsbn = Pair("subIsbn", data.subIsbn)
                )
                finishSearchActivity()
            }
        }
    }
}
