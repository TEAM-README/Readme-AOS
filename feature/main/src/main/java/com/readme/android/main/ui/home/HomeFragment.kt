package com.readme.android.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.ext.shortToast
import com.readme.android.core_ui.util.EventObserver
import com.readme.android.core_ui.util.ItemDecorationUtil
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.main.R
import com.readme.android.main.databinding.FragmentHomeBinding
import com.readme.android.main.ui.adapter.FeedAdapter
import com.readme.android.main.ui.feed.FeedDetailActivity
import com.readme.android.main.ui.feed.FeedDetailActivity.Companion.FEED_ID
import com.readme.android.main.view.MoreBottomSheetDialog
import com.readme.android.main.viewmodel.FeedViewModel
import com.readme.android.main.viewmodel.FeedViewModel.Companion.FAIL
import com.readme.android.main.viewmodel.FeedViewModel.Companion.SUCCESS
import com.readme.android.shared.R.string
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: FeedViewModel by activityViewModels()
    private lateinit var homeHeaderAdapter: HomeHeaderAdapter
    private lateinit var feedAdapter: FeedAdapter

    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        terminationTokenHandling(viewModel)
        initAdapter()
        observeFeedList()
        observeSelectedCategory()
        observeServerStatus()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHomeFeed()
    }

    private fun initAdapter() {
        homeHeaderAdapter = HomeHeaderAdapter(
            viewModel.isCategorySelected.value ?: false,
            viewModel.selectedCategoryString.value ?: "",
            ::onCategoryIconClick
        )
        feedAdapter = FeedAdapter(::onMoreClick, ::onClickFeed)
        val concatAdapter = ConcatAdapter(
            homeHeaderAdapter,
            feedAdapter.apply { submitList(viewModel.homeFeedInfoList.value) }
        )

        binding.rvHome.apply {
            addItemDecoration(ItemDecorationUtil.VerticalPlaceItemDecoration(16.dp))
            adapter = concatAdapter
        }
    }

    private fun onMoreClick(isMyFeed: Boolean, feedId: Int, feedWriterNickname: String?) {
        MoreBottomSheetDialog(isMyFeed, feedId, feedWriterNickname).show(
            childFragmentManager,
            this.tag
        )
    }

    private fun onClickFeed(id: Int) {
        val intent = Intent(activity, FeedDetailActivity::class.java).apply {
            putExtra(FEED_ID, id)
        }
        startActivity(intent)
    }

    private fun observeFeedList() {
        viewModel.homeFeedInfoList.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }
    }

    private fun observeServerStatus() {
        viewModel.isNetworkCorrespondenceEnd.observe(viewLifecycleOwner, EventObserver { message ->
            if (message == SUCCESS) {
                viewModel.getHomeFeed()
                requireContext().shortToast(getString(string.delete_feed_success))
            } else if (message == FAIL) requireContext().shortToast(getString(string.delete_feed_fail))
        })
    }

    private fun observeSelectedCategory() {
        viewModel.selectedCategoryChip.observe(viewLifecycleOwner) {
            viewModel.updateSelectedCategoryString()
            viewModel.setIsCategorySelected()
            homeHeaderAdapter.refreshCategoryData(
                viewModel.isCategorySelected.value ?: false,
                viewModel.selectedCategoryString.value ?: ""
            )
        }
    }

    private fun onCategoryIconClick() {
        FilterBottomSheetFragment().show(childFragmentManager, this.tag)
    }

}
