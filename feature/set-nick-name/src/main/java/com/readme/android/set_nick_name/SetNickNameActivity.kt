package com.readme.android.set_nick_name

import android.os.Bundle
import android.text.InputFilter
import androidx.activity.viewModels
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.constant.SetNickNameConstant.*
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.core_ui.util.EventObserver
import com.readme.android.navigator.MainNavigator
import com.readme.android.set_nick_name.databinding.ActivitySetNickNameBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class SetNickNameActivity :
    BindingActivity<ActivitySetNickNameBinding>(R.layout.activity_set_nick_name) {

    @Inject
    lateinit var mainNavigator: MainNavigator
    private val setNickNameViewModel by viewModels<SetNickNameViewModel>()
    private lateinit var socialToken: String
    private lateinit var platform: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setNickNameViewModel = setNickNameViewModel
        initExtraData()
        initEditTextFilter()
        initDuplicateNickNameButton()
        initStartButtonClickListener()
        initMoveToHomeObserver()
    }

    private fun initEditTextFilter() {
        binding.etSetNickname.filters = arrayOf(
            InputFilter { source, _, _, _, _, _ ->
                val noSpecialCharacterRegex = "^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]$"
                val noSpecialCharacterPattern = Pattern.compile(noSpecialCharacterRegex)
                if (source.isNullOrEmpty() || noSpecialCharacterPattern.matcher(source).matches()) {
                    if (binding.etSetNickname.text.length < 7) {
                        setNickNameViewModel.updateNickNameState(HAS_NO_STATE)
                    } else {
                        setNickNameViewModel.updateNickNameState(OVER_TEXT_LIMIT)
                    }
                    return@InputFilter source
                }
                setNickNameViewModel.updateNickNameState(NO_SPECIAL_CHARACTER)
                return@InputFilter ""
            },
            InputFilter.LengthFilter(7)
        )
    }

    private fun initDuplicateNickNameButton() {
        binding.tvCheckDuplicateButton.setOnSingleClickListener {
            setNickNameViewModel.checkDuplicateNickName()
        }
    }

    private fun initExtraData() {
        platform = intent.getStringExtra("platform") ?: ""
        socialToken = intent.getStringExtra("socialToken") ?: ""
    }

    private fun initStartButtonClickListener() {
        binding.btnStart.setOnSingleClickListener {
            setNickNameViewModel.postSignUp(platform, socialToken)
        }
    }

    private fun moveMainActicity() {
        mainNavigator.openMain(this)
        finish()
    }

    private fun initMoveToHomeObserver() {
        setNickNameViewModel.moveToHome.observe(
            this,
            EventObserver {
                moveMainActicity()
            }
        )
    }
}
