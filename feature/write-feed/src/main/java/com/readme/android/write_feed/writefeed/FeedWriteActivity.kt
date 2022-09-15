package com.readme.android.write_feed.writefeed

import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.constant.FeedWriteFragmentList.*
import com.readme.android.core_ui.ext.closeKeyboard
import com.readme.android.core_ui.util.KeyboardVisibilityUtils
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.shared.custom.ReadmeDialogFragment
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.ActivityFeedWriteBinding
import com.readme.android.write_feed.fragments.ChooseCategoryFragment
import com.readme.android.write_feed.fragments.ImpressiveSentenceFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedWriteActivity : BindingActivity<ActivityFeedWriteBinding>(R.layout.activity_feed_write) {

    private val feedWriteViewModel by viewModels<FeedWriteViewModel>()

    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.feedWriteViewModel = feedWriteViewModel
        feedWriteViewModel.getUserNickName()
        setFragmentContainerMargin()
        updateKeyBoardState()
        initStartFragment()
        initButtonBackClickListener()
        initExtraData()
        feedWriteActivity = this
    }

    override fun onBackPressed() {
        backButtonProcess()
    }

    private fun setFragmentContainerMargin() {
        binding.fragmentContainerMargin = resolutionMetrics.toPixel(112)
    }

    private fun updateKeyBoardState() {
        KeyboardVisibilityUtils(
            this.window,
            onShowKeyboard = {
                feedWriteViewModel.setKeyBoardState(false)
            },
            onHideKeyboard = {
                feedWriteViewModel.setKeyBoardState(true)
            }
        )
    }

    private fun initExtraData() {
        feedWriteViewModel.initBookInfo(
            title = intent.getStringExtra("title") ?: "",
            author = intent.getStringExtra("author") ?: "",
            image = intent.getStringExtra("image") ?: "",
            isbn = intent.getStringExtra("isbn") ?: "",
            subIsbn = intent.getStringExtra("subIsbn") ?: ""
        )
    }

    private fun initStartFragment() {
        supportFragmentManager.beginTransaction()
            .replace<ChooseCategoryFragment>(R.id.container_feed_write).commit()
    }

    private fun initButtonBackClickListener() {
        binding.btnBack.setOnClickListener {
            backButtonProcess()
        }
    }

    private fun backButtonProcess() {
        when (requireNotNull(feedWriteViewModel.currentFragment.value)) {
            CHOOSE_CATEGORY -> {
                ReadmeDialogFragment.newInstance(
                    title = getString(com.readme.android.shared.R.string.write_feed_dialog_title),
                    body = getString(com.readme.android.shared.R.string.write_feed_dialog_content),
                    positiveButtonClickListener = { finish() }
                ).show(
                    supportFragmentManager,
                    ReadmeDialogFragment.TAG
                )
            }
            IMPRESSIVE_SENTENCE -> {
                feedWriteViewModel.updateCurrentFragment(CHOOSE_CATEGORY)
                supportFragmentManager.beginTransaction()
                    .replace<ChooseCategoryFragment>(R.id.container_feed_write).commit()
            }
            FEELING -> {
                feedWriteViewModel.updateCurrentFragment(IMPRESSIVE_SENTENCE)
                supportFragmentManager.beginTransaction()
                    .replace<ImpressiveSentenceFragment>(R.id.container_feed_write).commit()
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus

        if (view != null && ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE && view is EditText && !view.javaClass.name.startsWith(
                "android.webkit."
            )
        ) {
            val locationList = IntArray(2)
            view.getLocationOnScreen(locationList)
            val x = ev.rawX + view.left - locationList[0]
            val y = ev.rawY + view.top - locationList[1]
            if (x < view.left || x > view.right || y < view.top || y > view.bottom) {
                closeKeyboard(view)
                view.clearFocus()
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    companion object {
        lateinit var feedWriteActivity: FeedWriteActivity
    }
}
