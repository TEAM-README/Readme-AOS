package com.readme.android.book_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import com.readme.android.book_search.databinding.ActivityBookSearchBinding
import com.readme.android.core.base.BindingActivity
import com.readme.android.core.util.ResolutionMetrics
import com.readme.android.domain.entity.BookInfo
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BookSearchActivity:
    BindingActivity<ActivityBookSearchBinding>(R.layout.activity_book_search) {

    private val bookSearchViewModel by viewModels<BookSearchViewModel>()
    private lateinit var bookListRecyclerViewAdapter: BookListRecyclerViewAdapter
    @Inject lateinit var resolutionMetrics: ResolutionMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bookSearchViewModel = bookSearchViewModel
        initBookListRecyclerViewAdapter()
        setRecyclerViewRecentReadMargin()
        updateRecyclerViewList()
        initBookSearchButtonListener()
    }


    private fun initBookListRecyclerViewAdapter() {
        bookListRecyclerViewAdapter = BookListRecyclerViewAdapter()
        binding.rvBookList.adapter = bookListRecyclerViewAdapter
    }



    private fun setRecyclerViewRecentReadMargin(){
        binding.tvRecentRead.viewTreeObserver.addOnGlobalLayoutListener(object :ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                binding.recentReadVisibleMargin = resolutionMetrics.toPixel(36 + 6) + binding.tvRecentRead.height
                binding.recentReadGoneMargin = resolutionMetrics.toPixel(36)
                binding.tvRecentRead.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun initBookSearchButtonListener(){
        binding.btnBookSearch.setOnClickListener {
            bookSearchViewModel.getBookSearchList()
        }
    }

    private fun updateRecyclerViewList(){
        bookSearchViewModel.bookSearchList.observe(this){
            bookListRecyclerViewAdapter.submitList(it)
        }
    }
}
