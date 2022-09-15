package com.readme.android.book_search

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.MotionEvent
import android.view.ViewTreeObserver
import android.widget.EditText
import androidx.activity.viewModels
import com.readme.android.book_search.databinding.ActivityBookSearchBinding
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.ext.closeKeyboard
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.core_ui.util.KeyBoardUtil
import com.readme.android.core_ui.util.ResolutionMetrics
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BookSearchActivity :
    BindingActivity<ActivityBookSearchBinding>(R.layout.activity_book_search) {

    private val bookSearchViewModel by viewModels<BookSearchViewModel>()
    private lateinit var bookListRecyclerViewAdapter: BookListRecyclerViewAdapter

    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        terminationTokenHandling(bookSearchViewModel)
        binding.bookSearchViewModel = bookSearchViewModel
        initBookListRecyclerViewAdapter()
        setRecyclerViewRecentReadMargin()
        updateRecyclerViewList()
        initQuitButtonClickListener()
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

    private fun initQuitButtonClickListener() {
        binding.btnClose.setOnClickListener { finish() }
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

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus

        if (view != null && ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE && view is EditText && !view.javaClass.name.startsWith(
                "android.webkit."
            )
        ) {
            val locationList = IntArray(2)
            view.getLocationOnScreen(locationList)
            val x = ev.rawX + view.left - locationList[0]
            val y = ev.rawY + view.top - locationList[1]
            if (x < view.left || x > view.right || y < view.top || y > view.bottom) {
                closeKeyboard(view)
                view.clearFocus()
            }
        }

        return super.dispatchTouchEvent(ev)
    }
}
