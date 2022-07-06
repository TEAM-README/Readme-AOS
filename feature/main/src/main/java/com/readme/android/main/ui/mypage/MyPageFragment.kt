package com.readme.android.main.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.util.ItemDecorationUtil
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.domain.entity.Feed
import com.readme.android.domain.entity.MyPageUser
import com.readme.android.main.R
import com.readme.android.main.databinding.FragmentMyPageBinding
import com.readme.android.main.ui.adapter.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val Number.dp: Int
        get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        // TODO 데이터 연동 로직 + adapter 변수 스코프 수정
        val myPageTopAdapter = MyPageTopAdapter(MyPageUser(Int.MAX_VALUE, "문다빙빙테스트용", 5))
        val feedAdapter = FeedAdapter()
        val concatAdapter = ConcatAdapter(
            myPageTopAdapter,
            feedAdapter.apply {
                submitList(
                    listOf(
                        Feed(
                            0,
                            "에세이",
                            "1cm 다빙빙",
                            "문다빈 그는 신인가?",
                            "개발 죽고싶넹",
                            "문다빙빙테스트용",
                            "2021/10/31",
                            true
                        ),
                        Feed(
                            0,
                            "에세이",
                            "1cm 다빙빙",
                            "문다빈 그는 신인가?",
                            "개발 죽고싶넹",
                            "문다빙빙테스트용",
                            "2021/10/31",
                            true
                        ),
                        Feed(
                            0,
                            "에세이",
                            "1cm 다빙빙",
                            "문다빈 그는 신인가?",
                            "개발 죽고싶넹",
                            "문다빙빙테스트용",
                            "2021/10/31",
                            true
                        )
                    )
                )
            }
        )

        binding.rvMypage.apply {
            addItemDecoration(ItemDecorationUtil.VerticalPlaceItemDecoration(16.dp))
            adapter = concatAdapter
        }
    }
}
