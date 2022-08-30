package com.readme.android.write_feed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.readme.android.shared.R.drawable.shape_rect_white_fill_14
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentWriteFeedDialogBinding

class WriteFeedDialogFragment : DialogFragment() {

    private var _binding: FragmentWriteFeedDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_write_feed_dialog, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCancelButtonListener()
        initCheckButtonListener()
    }

    override fun onStart() {
        super.onStart()
        setLayout()
    }

    private fun setLayout() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    (resources.displayMetrics.widthPixels * 0.91).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setBackgroundDrawableResource(shape_rect_white_fill_14)
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
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
