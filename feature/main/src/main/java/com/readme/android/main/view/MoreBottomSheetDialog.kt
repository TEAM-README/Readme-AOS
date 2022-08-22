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
class MoreBottomSheetDialog(
    private val isMyFeed: Boolean,
    private val feedWriterNickname: String? = null,
    private val feedId: Int? = null
) : BottomSheetDialogFragment() {

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
            data = Uri.parse(
                "mailto:${getString(string.readme_mail_address)}?" +
                        "subject=${getString(string.mail_title)}&" +
                        "body=:경광등:신고 유형 사유가 무엇인가요?\n" +
                        " ex) 상업적 광고 및 판매, 음란물/불건전한 대화, 욕설 비하, 도배, 부적절한 내용, 기타사유 등\n" +
                        "신고하신 사항은 리드미팀이 신속하게 처리하겠습니다. 감사합니다\n" +
                        "----------------------------------------------------------------------\n" +
                        "❗️이곳은 수정하지 말아주세요❗️\n" +
                        "신고할 유저의 닉네임 : ${feedWriterNickname}\n" +
                        "신고할 게시글의 id : $feedId"
            )
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) startActivity(intent)
        else requireContext().shortToast(getString(string.problem_occured))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
