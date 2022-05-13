package com.readme.android.main.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.readme.android.core.base.BindingFragment
import com.readme.android.core.util.ItemDecorationUtil
import com.readme.android.core.util.ResolutionMetrics
import com.readme.android.domain.entity.Feed
import com.readme.android.main.R
import com.readme.android.main.databinding.FragmentHomeBinding
import com.readme.android.main.ui.adapter.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        // TODO 데이터 연동 로직 + adapter 변수 스코프 수정
        val myPageTopAdapter = HomeHeaderAdapter(false)
        val feedAdapter = FeedAdapter()
        val concatAdapter = ConcatAdapter(
            myPageTopAdapter,
            feedAdapter.apply {
                submitList(
                    listOf(
                        Feed(
                            0,
                            "에세이",
                            "1cm 다이빙",
                            "‘스마트폰보다 재미있는게 있을까' 이것만큼 어려운 주제가 없다는 것을 안다. 하지만 그래도 답하고 싶었던 이유는, 카톡 속ㅋㅋㅋ가 아닌, 실제로 웃을 수 있는 상황을 바랐기 때문이 아닐까 ",
                            "나만의 시간을 보내고 싶어서 휴학을 했어요. 하지만 밤새 유튜브 보고 새벽 5시에 잠들고 오후 3시에 일어나는게 반복돼요. 스마트폰이 제 시간을 뺏어간다는 느낌이 들어 회의감이 자주 들었어요. 그래서 저 문장이 유독 공감갔고, '스마트폰보다 재미있는게 있을까'란 질문에 생각을 해봤지만 생각이 잘 안나더라구용. 한번 찾아보려합니다.",
                            "닉넴닉넴닉넴",
                            "2021/10/31",
                            false
                        ),
                        Feed(
                            0,
                            "에세이",
                            "1cm 다이빙",
                            "문다빈 그는 신인가?",
                            "takeaway",
                            "닉넴닉넴닉넴",
                            "2021/10/31",
                            false
                        ),
                        Feed(
                            0,
                            "에세이",
                            "1cm 다이빙",
                            "문다빈 그는 신인가?",
                            "takeaway",
                            "닉넴닉넴닉넴",
                            "2021/10/31",
                            false
                        )
                    )
                )
            }
        )

        binding.rvHome.apply {
            addItemDecoration(ItemDecorationUtil.VerticalPlaceItemDecoration(16.dp))
            adapter = concatAdapter
        }
    }
}
