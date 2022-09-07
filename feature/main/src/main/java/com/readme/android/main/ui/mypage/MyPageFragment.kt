package com.readme.android.main.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.ext.shortToast
import com.readme.android.core_ui.util.ItemDecorationUtil
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.core_ui.util.UiState
import com.readme.android.main.R
import com.readme.android.main.databinding.FragmentMyPageBinding
import com.readme.android.main.ui.adapter.FeedAdapter
import com.readme.android.main.ui.feed.FeedDetailActivity
import com.readme.android.main.ui.feed.FeedDetailActivity.Companion.FEED_ID
import com.readme.android.main.view.MoreBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyPageFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel: MyPageViewModel by viewModels()
    private val myPageTopAdapter = MyPageTopAdapter()
    private val feedAdapter = FeedAdapter(::onMoreClick, ::onClickFeed)
    private val concatAdapter = ConcatAdapter(myPageTopAdapter, feedAdapter)

    private val Number.dp: Int
        get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMyPageInfo()
    }

    private fun observeData() {
        viewModel.myPageUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    myPageTopAdapter.myPageUser = it.data.myPageUser
                    feedAdapter.submitList(it.data.feedList)
                }
                is UiState.Failure -> {
                    it.msg?.let { msg ->
                        requireContext().shortToast(msg)
                    }
                }
                else -> {
                    // TODO : 로딩 로직
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isFeedEmpty.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            binding.isFeedEmpty = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initView() {
        binding.rvMypage.apply {
            addItemDecoration(ItemDecorationUtil.VerticalPlaceItemDecoration(16.dp))
            adapter = concatAdapter
        }
    }

    private fun onMoreClick(
        isMyFeed: Boolean = true,
        feedId: Int,
        feedWriterName: String? = null
    ) {
        MoreBottomSheetDialog(isMyFeed, feedId, feedWriterName).show(childFragmentManager, this.tag)
    }

    private fun onClickFeed(id: Int) {
        val intent = Intent(activity, FeedDetailActivity::class.java).apply {
            putExtra(FEED_ID, id)
        }
        startActivity(intent)
    }
}
