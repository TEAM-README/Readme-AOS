package com.readme.android.main.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.readme.android.main.R
import com.readme.android.main.databinding.LayoutMoreBottomSheetBinding
import com.readme.android.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MoreBottomSheetDialog : BottomSheetDialogFragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: LayoutMoreBottomSheetBinding? = null
    protected val binding: LayoutMoreBottomSheetBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.layout_more_bottom_sheet, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewModel.isMyFeed.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                with(binding) {
                    isMyFeed = it
                    when (it) {
                        true -> tvAction.setOnClickListener {
                            // TODO 삭제하기 로직 넣기
                            dismiss()
                        }
                        else -> tvAction.setOnClickListener {
                            Log.d(TAG, "observeData: click")
                            dismiss()
                            sendMail()
                        }
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun sendMail() {
        val intent = Intent().apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("readmeteam@naver.com"))
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) startActivity(intent)
        else Toast.makeText(requireContext(), "문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
