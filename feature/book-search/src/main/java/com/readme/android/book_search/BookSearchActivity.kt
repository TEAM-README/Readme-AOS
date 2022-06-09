package com.readme.android.book_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import com.readme.android.book_search.databinding.ActivityBookSearchBinding
import com.readme.android.core.base.BindingActivity
import com.readme.android.core.util.ResolutionMetrics
import com.readme.android.domain.entity.BookInfo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookSearchActivity:
    BindingActivity<ActivityBookSearchBinding>(R.layout.activity_book_search) {

    private lateinit var bookListRecyclerViewAdapter: BookListRecyclerViewAdapter
    @Inject lateinit var resolutionMetrics: ResolutionMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBookListRecyclerViewAdapter()
        setRecyclerViewRecentReadMargin()
        templistupdate()
    }


    private fun initBookListRecyclerViewAdapter() {
        bookListRecyclerViewAdapter = BookListRecyclerViewAdapter()
        binding.rvBookList.adapter = bookListRecyclerViewAdapter
    }

    private fun templistupdate() {
        bookListRecyclerViewAdapter.submitList(
            listOf(
                BookInfo("제목1", "저자", "http://img.khan.co.kr/news/2011/12/15/met300.gif"),
                BookInfo("제목1", "저자", "http://img.khan.co.kr/news/2011/12/15/met300.gif"),
                BookInfo("제목1", "저자", "http://img.khan.co.kr/news/2011/12/15/met300.gif"),
                BookInfo("제목1", "저자", "http://img.khan.co.kr/news/2011/12/15/met300.gif"),
                BookInfo("제목1", "저자", "http://img.khan.co.kr/news/2011/12/15/met300.gif"),
                )
        )
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
}
