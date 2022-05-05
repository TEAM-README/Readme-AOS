package com.readme.android.core.base

import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.readme.android.core.ext.closeKeyboard
import com.readme.android.core.util.Injector
import com.readme.android.core.util.ResolutionMetrics
import dagger.hilt.android.EntryPointAccessors

abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: T
    private val resolutionMetrics: ResolutionMetrics by lazy {
        EntryPointAccessors.fromActivity(
            this,
            Injector.ResolutionMetricsInjector::class.java
        ).resolutionMetrics()
    }

    val Number.dp: Int
        get() = resolutionMetrics.toPixel(this.toInt())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus

        if (view != null && ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE && view is EditText && !view.javaClass
                .name.startsWith("android.webkit.")
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
}
