package com.readme.android.core_ui.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.readme.android.core_ui.ext.closeKeyboard
import com.readme.android.core_ui.util.EventObserver
import com.readme.android.core_ui.util.Injector
import com.readme.android.navigator.MainNavigator
import dagger.hilt.android.EntryPointAccessors

abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    val mainNavigator: MainNavigator by lazy {
        EntryPointAccessors.fromActivity(
            this,
            Injector.MainNavigaterInjector::class.java
        ).mainNavigator()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EntryPointAccessors.fromActivity(
            this,
            Injector.SharedPreferencesInjector::class.java
        ).sharedPreferences()
    }

    protected fun terminationTokenHandling(viewModel: BaseViewModel) {
        viewModel.moveToLogin.observe(this, EventObserver {
            mainNavigator.openLogin(this)
            sharedPreferences.edit { remove("READ_ME_ACCESS_TOKEN") }
            sharedPreferences.edit { remove("USER_NICKNAME") }
            finishAffinity()
        })
    }
}
