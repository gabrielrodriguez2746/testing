package com.example.developer.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView

class TestConstraintActivity : AppCompatActivity() {

    private var count = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contraint_test)
        findViewById<View>(R.id.button).setOnClickListener {
            findViewById<ViewGroup>(R.id.layout_container).addView(TextView(this).apply {
                layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
                text = "$count text"
            })
        }
    }

}