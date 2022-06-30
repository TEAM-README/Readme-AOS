package com.readme.android.core_ui.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.readme.android.core_ui.util.EventObserver
import com.readme.android.core_ui.util.Injector
import com.readme.android.navigator.MainNavigator
import dagger.hilt.android.EntryPointAccessors

abstract class BindingFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private val mainNavigator: MainNavigator by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(),
            Injector.MainNavigaterInjector::class.java
        ).mainNavigator()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(),
            Injector.SharedPreferencesInjector::class.java
        ).sharedPreferences()
    }

    fun terminationTokenHandling (viewModel: BaseViewModel){
        viewModel.moveToLogin.observe(viewLifecycleOwner, EventObserver{
            mainNavigator.openLogin(requireActivity())
            sharedPreferences.edit().remove("READ_ME_ACCESS_TOKEN").apply()
            sharedPreferences.edit().remove("USER_NICKNAME").apply()
            requireActivity().finish()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
