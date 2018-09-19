package com.example.developer.myapplication

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import android.view.View

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

    init {
        View.inflate(context, R.layout.layout_scheleton_open, this)
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