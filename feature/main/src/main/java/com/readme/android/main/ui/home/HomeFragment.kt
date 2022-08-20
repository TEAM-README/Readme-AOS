package com.readme.android.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.util.ItemDecorationUtil
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.main.R
import com.readme.android.main.databinding.FragmentHomeBinding
import com.readme.android.main.ui.adapter.FeedAdapter
import com.readme.android.main.ui.feed.FeedDetailActivity
import com.readme.android.main.ui.feed.FeedDetailActivity.Companion.FEED_ID
import com.readme.android.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var feedAdapter: FeedAdapter

    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        initAdapter()
        observeFeedList()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHomeFeed()
    }

    private fun initAdapter() {
        val homeHeaderAdapter = HomeHeaderAdapter(
            viewModel.getIsCategorySelected(),
            viewModel.getSelectedCategory(),
            ::onCategoryIconClick
        )
        feedAdapter = FeedAdapter(::onClickFeed)
        val concatAdapter = ConcatAdapter(
            homeHeaderAdapter,
            feedAdapter.apply { submitList(viewModel.homeFeedList.value) }
        )

        binding.rvHome.apply {
            addItemDecoration(ItemDecorationUtil.VerticalPlaceItemDecoration(16.dp))
            adapter = concatAdapter
        }
    }

    private fun onClickFeed(id: Int) {
        val intent = Intent(activity, FeedDetailActivity::class.java).apply {
            putExtra(FEED_ID, id)
        }
        startActivity(intent)
    }

    private fun observeFeedList() {
        viewModel.homeFeedList.observe(requireActivity()) {
            feedAdapter.submitList(it)
        }
    }

    private fun onCategoryIconClick() {
        FilterBottomSheetFragment().show(childFragmentManager, this.tag)
    }

}
