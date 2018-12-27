package com.project.developer.myapplication

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.project.developer.myapplication.R.id.root
import com.project.developer.myapplication.R.id.textView_header_title

class TestActivityV2 : AppCompatActivity() {

    private var open = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_open)

        findViewById<View>(textView_header_title).setOnClickListener { _ ->
            ConstraintSet().apply {
                val layout = findViewById<ConstraintLayout>(root)
                clone(this@TestActivityV2, R.layout.layout_closed.takeIf { open }
                        ?: R.layout.layout_open)
                applyTo(layout)
                TransitionManager.beginDelayedTransition(layout)
                open = !open
            }
        }
    }

}