package com.readme.android.set_nick_name

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.readme.android.core.base.BindingActivity
import com.readme.android.core.constant.HAS_NO_STATE
import com.readme.android.core.constant.NO_SPECIAL_CHARACTER
import com.readme.android.core.constant.OVER_TEXT_LIMIT
import com.readme.android.set_nick_name.databinding.ActivitySetNickNameBinding
import java.util.regex.Pattern


class SetNickNameActivity :
    BindingActivity<ActivitySetNickNameBinding>(R.layout.activity_set_nick_name) {

    private val setNickNameViewModel by viewModels<SetNickNameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setNickNameViewModel = setNickNameViewModel
        binding.lifecycleOwner = this

        initEditTextFilter()
//        initTextChangedListener()

    }

    private fun initEditTextFilter() {
        binding.etSetNickname.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            val noSpecialCharacterRegex = "^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]$"
            val noSpecialCharacterPattern = Pattern.compile(noSpecialCharacterRegex)
            if (source.equals("") || noSpecialCharacterPattern.matcher(source).matches()) {
                    setNickNameViewModel.updateNickNameState(HAS_NO_STATE)
                    return@InputFilter source
            }
            setNickNameViewModel.updateNickNameState(NO_SPECIAL_CHARACTER)
            ""
        }, InputFilter.LengthFilter(8)
        )
    }

//    private fun initTextChangedListener(){
//        binding.etSetNickname.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun afterTextChanged(p0: Editable?) {
//                if(p0.toString().length >= 7){
//                    setNickNameViewModel.updateNickNameState(OVER_TEXT_LIMIT)
//                }
//            }
//        })
//    }

}