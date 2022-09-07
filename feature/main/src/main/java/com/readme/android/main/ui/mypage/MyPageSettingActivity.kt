package com.readme.android.main.ui.mypage

import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.ext.shortToast
import com.readme.android.core_ui.ext.startMail
import com.readme.android.core_ui.ext.startUri
import com.readme.android.main.R
import com.readme.android.main.databinding.ActivityMyPageSettingBinding
import com.readme.android.shared.custom.ReadmeDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageSettingActivity :
    BindingActivity<ActivityMyPageSettingBinding>(R.layout.activity_my_page_setting) {
    private val viewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        terminationTokenHandling(viewModel)
        initClickEvent()
        observeData()
    }

    private fun initClickEvent() {
        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }

            tvInquire.setOnClickListener {
                startMail(
                    EXTRA_SUBJECT to getString(com.readme.android.shared.R.string.inquire_subject),
                    EXTRA_TEXT to getString(com.readme.android.shared.R.string.inquire_body)
                )
            }

            tvSeeTerms.setOnClickListener {
                startUri("https://paint-red-74c.notion.site/e187f3913b914e869cf48c9bf10fc751")
            }

            tvLogout.setOnClickListener {
                showDialog(
                    title = getString(com.readme.android.shared.R.string.logout_dialog_title),
                    body = getString(com.readme.android.shared.R.string.logout_dialog_content),
                    ::startLogout
                )
            }

            tvDeleteUser.setOnClickListener {
                showDialog(
                    title = getString(com.readme.android.shared.R.string.delete_user_dialog_title),
                    body = getString(com.readme.android.shared.R.string.delete_user_dialog_content),
                    ::deleteUser
                )
            }
        }
    }

    private fun showDialog(title: String, body: String, positiveButtonClickListener: () -> Unit) {
        ReadmeDialogFragment.newInstance(
            title = title,
            body = body,
            positiveButtonClickListener = positiveButtonClickListener
        ).show(
            supportFragmentManager,
            ReadmeDialogFragment.TAG
        )
    }

    private fun deleteUser() {
        viewModel.deleteUser()
    }

    private fun startLogout() {
        lifecycleScope.launch {
            viewModel.clearUserInfo()
        }
    }

    private fun observeData() {
        viewModel.isLogOutEvent.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is LogOutState.Success -> {
                    mainNavigator.openLogin(this)
                    finishAffinity()
                }
                is LogOutState.Failure -> {
                    it.msg?.let { msg ->
                        shortToast(msg)
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }
}
