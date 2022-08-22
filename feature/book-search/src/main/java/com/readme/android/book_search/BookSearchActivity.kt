package com.readme.android.book_search

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import com.readme.android.book_search.databinding.ActivityBookSearchBinding
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.core_ui.util.KeyBoardUtil
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.navigator.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookSearchActivity :
    BindingActivity<ActivityBookSearchBinding>(R.layout.activity_book_search) {

    private val bookSearchViewModel by viewModels<BookSearchViewModel>()
    private lateinit var bookListRecyclerViewAdapter: BookListRecyclerViewAdapter

    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        terminationTokenHandling(bookSearchViewModel)
        binding.bookSearchViewModel = bookSearchViewModel
        initBookListRecyclerViewAdapter()
        setRecyclerViewRecentReadMargin()
        updateRecyclerViewList()
        initBookSearchButtonListener()
        initEditTextBookSearchFocusListener()
        getRecentReadList()
        initEnterKeyListener()
    }

    private fun initBookListRecyclerViewAdapter() {
        bookListRecyclerViewAdapter = BookListRecyclerViewAdapter(mainNavigator) { finish() }
        binding.rvBookList.adapter = bookListRecyclerViewAdapter
    }

    private fun setRecyclerViewRecentReadMargin() {
        binding.tvRecentRead.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.recentReadVisibleMargin =
                        resolutionMetrics.toPixel(36 + 6) + binding.tvRecentRead.height
                    binding.recentReadGoneMargin = resolutionMetrics.toPixel(36)
                    binding.tvRecentRead.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
    }

    private fun initBookSearchButtonListener() {
        binding.btnBookSearch.setOnSingleClickListener {
            if (bookSearchViewModel.searchWord.value != "") {
                bookSearchViewModel.getBookSearchList()
            }
            KeyBoardUtil.hide(this)
        }
    }

    private fun initEnterKeyListener() {
        binding.etBookSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                if (bookSearchViewModel.searchWord.value != "") {
                    bookSearchViewModel.getBookSearchList()
                }
                KeyBoardUtil.hide(this)
            }
            false
        }
    }

    private fun updateRecyclerViewList() {
        bookSearchViewModel.bookSearchList.observe(this) {
            bookListRecyclerViewAdapter.submitList(it)
        }
    }

    private fun initEditTextBookSearchFocusListener() {
        binding.etBookSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                bookSearchViewModel.setBookSearchTextState(false)
                bookSearchViewModel.setBookSearchVisibilityState(true)
                bookListRecyclerViewAdapter.submitList(listOf())
                bookSearchViewModel.setBookSearchCurrentReadState(false)
                bookSearchViewModel.searchWord.postValue("")
            }
        }
    }

    private fun getRecentReadList() {
        bookSearchViewModel.getRecentReadList()
    }
}
