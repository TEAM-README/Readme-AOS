package com.readme.android.shared.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.readme.android.shared.R
import com.readme.android.shared.databinding.FragmentReadmeDialogBinding

class ReadmeDialogFragment : DialogFragment() {
    private var _binding: FragmentReadmeDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_readme_dialog, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initCancelButtonListener()
        initCheckButtonListener()
    }

    private fun initView() {
        binding.tvTitle.text = title
        binding.tvContent.text = body
        setLayout()
    }

    private fun setLayout() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    (resources.displayMetrics.widthPixels * 0.91).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setBackgroundDrawableResource(R.drawable.shape_rect_white_fill_14)
            }
        }
    }

    private fun initCancelButtonListener() {
        binding.tvCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initCheckButtonListener() {
        binding.tvCheck.setOnClickListener {
            dismiss()
            positiveButtonClickListener()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ReadmeDialog"
        private var title = ""
        private var body = ""
        private lateinit var positiveButtonClickListener: () -> Unit

        fun newInstance(
            title: String,
            body: String,
            positiveButtonClickListener: () -> Unit
        ): ReadmeDialogFragment {
            this.title = title
            this.body = body
            this.positiveButtonClickListener = positiveButtonClickListener
            return ReadmeDialogFragment()
        }
    }
}
