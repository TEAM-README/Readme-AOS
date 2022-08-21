package com.readme.android.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.readme.android.core_ui.ext.shortToast
import com.readme.android.main.R
import com.readme.android.main.databinding.LayoutMoreBottomSheetBinding
import com.readme.android.shared.R.string
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreBottomSheetDialog(private val isMyFeed: Boolean) : BottomSheetDialogFragment() {

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
        binding.isMyFeed = this.isMyFeed
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        when (isMyFeed) {
            true -> binding.tvAction.setOnClickListener {
                // TODO 삭제하기 로직 넣기
                dismiss()
            }
            false -> binding.tvAction.setOnClickListener {
                dismiss()
                sendMail()
            }
        }
    }


    private fun sendMail() {
        val intent = Intent().apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(string.readme_mail_address)))
            putExtra(Intent.EXTRA_SUBJECT, getString(string.mail_title))
            putExtra(Intent.EXTRA_TEXT, getString(string.mail_text))
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) startActivity(intent)
        else requireContext().shortToast(getString(string.problem_occured))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
