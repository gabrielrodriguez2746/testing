package com.project.developer.myapplication

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.appbar.AppBarLayout
import android.util.AttributeSet

class CollapsibleConstrainLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    private val transitionThreshold = 0.55f
    private var lastPosition = 0
    private var isToolbarOpen = true

    private var openToolbarSet = ConstraintSet().apply {
        clone(context, R.layout.layout_open)
    }
    private var closedToolbarSet = ConstraintSet().apply {
        clone(context, R.layout.layout_closed)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (lastPosition != verticalOffset) {
            lastPosition = verticalOffset
            val progress = Math.abs(verticalOffset / appBarLayout.height.toFloat())

            val parameters = layoutParams as AppBarLayout.LayoutParams
            parameters.topMargin = -verticalOffset
            layoutParams = parameters

            if (isToolbarOpen && progress > transitionThreshold) {
                closedToolbarSet.applyTo(this)
                isToolbarOpen = false
            } else if (!isToolbarOpen && progress < transitionThreshold) {
                openToolbarSet.applyTo(this)
                isToolbarOpen = true
            }
        }
    }

}