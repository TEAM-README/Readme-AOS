package com.readme.android.write_feed.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import com.google.android.material.chip.Chip
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.constant.Category
import com.readme.android.core_ui.constant.FeedWriteFragmentList.IMPRESSIVE_SENTENCE
import com.readme.android.core_ui.ext.getColor
import com.readme.android.shared.R.color
import com.readme.android.write_feed.writefeed.FeedWriteViewModel
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentChooseCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseCategoryFragment :
    BindingFragment<FragmentChooseCategoryBinding>(R.layout.fragment_choose_category) {

    private val feedWriteViewModel by activityViewModels<FeedWriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.feedWriteViewModel = feedWriteViewModel
        initNextButtonClickListener()
        setChips()
        onClickChip()
        restoreSelectedChipGroup()
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
            feedWriteViewModel.setCategoryList(getSelectedChipList())
            feedWriteViewModel.setWholeCategoryString(categoryListToString())
            feedWriteViewModel.setRepresentCategoryString()
            feedWriteViewModel.updateCurrentFragment(IMPRESSIVE_SENTENCE)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace<ImpressiveSentenceFragment>(R.id.container_feed_write).commit()
        }
    }

    private fun setChips() {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_selected),
            intArrayOf(-android.R.attr.state_selected)
        )
        val backgroundColors = intArrayOf(getColor(color.chip_blue), getColor(color.chip_gray))
        val textColors = intArrayOf(getColor(color.main_blue), getColor(color.gray_2))
        val backgroundStateList = ColorStateList(states, backgroundColors)
        val textStateList = ColorStateList(states, textColors)

        for (i in 0 until Category.values().size) {
            binding.chipGroup.addView(
                Chip(requireContext()).apply {
                    this.text = Category.values()[i].categoryName
                    this.chipStartPadding = 35F
                    this.chipEndPadding = 35F
                    this.chipBackgroundColor = backgroundStateList
                    this.setTextColor(textStateList)
                    this.setTextAppearance(com.readme.android.shared.R.style.regular_01)
                    this.setRippleColorResource(android.R.color.transparent)
                }
            )
        }
    }

    private fun onClickChip() {
        for (i in 0 until binding.chipGroup.size) {
            binding.chipGroup[i].setOnClickListener {
                it.isSelected = !it.isSelected
                val tempList = feedWriteViewModel.categoryList.value ?: mutableListOf()
                val category = (it as Chip).text.toString()
                if (it.isSelected) tempList.add(category)
                else tempList.remove(category)
                feedWriteViewModel.setCategoryList(tempList)
            }
        }
    }

    private fun getSelectedChipList(): MutableList<String> {
        val selectedChipList: MutableList<String> = mutableListOf()
        for (i in 0 until Category.values().size)
            if (binding.chipGroup[i].isSelected) selectedChipList.add(Category.values()[i].categoryName)
        return selectedChipList
    }

    private fun restoreSelectedChipGroup() {
        for (i in 0 until Category.values().size) {
            val chip = binding.chipGroup[i] as Chip
            if (requireNotNull(feedWriteViewModel.categoryList.value).contains(chip.text)){
                binding.chipGroup[i].isSelected = true
            }
        }
    }

    private fun categoryListToString(): String =
        requireNotNull(feedWriteViewModel.categoryList.value).joinToString(SEPARATOR)

    companion object {
        const val SEPARATOR = ","
    }
}
