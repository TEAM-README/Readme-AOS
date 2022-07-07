package com.readme.android.main.ui.home

import android.content.ContentValues.TAG
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.readme.android.core_ui.constant.Category
import com.readme.android.core_ui.ext.getColor
import com.readme.android.main.databinding.FragmentFilterBottomSheetBinding
import com.readme.android.main.viewmodel.MainViewModel
import com.readme.android.shared.R.color
import com.readme.android.shared.R.style.regular_01

class FilterBottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentFilterBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setBottomSheetHeight()
        setChips()
        onClickResetArea()
        onClickChip()
        onClickApplyBtn()
    }

    private fun setChips() {

        val states = arrayOf(
            intArrayOf(android.R.attr.state_selected),
            intArrayOf(-android.R.attr.state_selected),
        )
        val backgroundColors = intArrayOf(getColor(color.chip_blue), getColor(color.chip_gray))
        val textColors = intArrayOf(getColor(color.main_blue), getColor(color.gray_2))
        val backgroundStateList = ColorStateList(states, backgroundColors)
        val textStateList = ColorStateList(states, textColors)

        for (i in 0 until Category.values().size) {
            binding.chipCategory.addView(
                Chip(requireContext()).apply {
                    this.text = Category.values()[i].categoryName
                    this.chipStartPadding = 35F
                    this.chipEndPadding = 35F
                    this.chipBackgroundColor = backgroundStateList
                    this.setTextColor(textStateList)
                    this.setTextAppearance(regular_01)
                    this.setRippleColorResource(android.R.color.transparent)
                }
            )
        }
    }

    private fun onClickResetArea() {
        binding.clResetFilter.setOnClickListener {
            Log.d(TAG, "onClickResetArea: ")
            for (i in 0 until binding.chipCategory.size) {
                binding.chipCategory[i].isSelected = false
            }
        }
    }

    private fun onClickChip() {
        for (i in 0 until binding.chipCategory.size) {
            binding.chipCategory[i].setOnClickListener {
                it.isSelected = !it.isSelected
                Log.d(TAG, "onClickChip: $i")
            }
        }
    }

    private fun onClickApplyBtn() {
        binding.btnApply.setOnClickListener {
            val selectedChipList = getSelectedChipList()
            viewModel.setSelectedCategoryChip(selectedChipList)

            Toast.makeText(requireContext(), "서비스 준비중입니다", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun getSelectedChipList(): List<String> {
        val selectedChipList: MutableList<String> = ArrayList()
        for (i in 0 until Category.values().size)
            if (binding.chipCategory[i].isSelected) selectedChipList.add(Category.values()[i].categoryName)
        return selectedChipList
    }

    private fun setBottomSheetHeight() {
        (dialog as BottomSheetDialog).behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED // 높이 고정
            skipCollapsed = true // HALF_EXPANDED 안되게 설정
        }
        binding.root.layoutParams.height = (resources.displayMetrics.heightPixels * 0.67).toInt()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
