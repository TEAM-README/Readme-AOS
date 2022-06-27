package com.readme.android.set_nick_name

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.constant.HAS_NO_STATE
import com.readme.android.core_ui.constant.NO_SPECIAL_CHARACTER
import com.readme.android.core_ui.constant.OVER_TEXT_LIMIT
import com.readme.android.set_nick_name.databinding.ActivitySetNickNameBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class SetNickNameActivity :
    BindingActivity<ActivitySetNickNameBinding>(R.layout.activity_set_nick_name) {

    private val setNickNameViewModel by viewModels<SetNickNameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setNickNameViewModel = setNickNameViewModel

        initEditTextFilter()


    }

    private fun initEditTextFilter() {
        binding.etSetNickname.filters = arrayOf(
            InputFilter { source, _, _, _, _, _ ->
                val noSpecialCharacterRegex = "^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]$"
                val noSpecialCharacterPattern = Pattern.compile(noSpecialCharacterRegex)
                if (source.isNullOrEmpty() || noSpecialCharacterPattern.matcher(source).matches()) {
                    if (binding.etSetNickname.text.length < 8) {
                        setNickNameViewModel.updateNickNameState(HAS_NO_STATE)
                    }else {
                        setNickNameViewModel.updateNickNameState(OVER_TEXT_LIMIT)
                    }
                    return@InputFilter source
                }
                setNickNameViewModel.updateNickNameState(NO_SPECIAL_CHARACTER)
                return@InputFilter ""
            }, InputFilter.LengthFilter(8)
        )
    }

}
