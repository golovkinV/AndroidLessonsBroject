package com.study.androidstudy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

class FragmentContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRef: Int = 0
) : FrameLayout(context, attrs, defStyleRef) {
    private val hierarchyChangeListener = object : OnHierarchyChangeListener {
        override fun onChildViewRemoved(parent: View, child: View) {}

        override fun onChildViewAdded(parent: View, child: View) {
            child.requestApplyInsets()
        }
    }

    init {
        setOnHierarchyChangeListener(hierarchyChangeListener)
    }

    @RequiresApi(20)
    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.dispatchApplyWindowInsets(WindowInsets(insets))
        }
        return insets
    }
}