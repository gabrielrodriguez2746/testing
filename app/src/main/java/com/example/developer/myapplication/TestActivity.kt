package com.example.developer.myapplication

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import com.example.developer.myapplication.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private var isDialogOpened = false

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)
    }

    private val viewModel by lazy {
        TestViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.layoutInformation.setOnClickListener {

            ConstraintSet().apply {
                clone(this@TestActivity, R.layout.dialog_contraint_open)
                applyTo(binding.layoutDialog)
                TransitionManager.beginDelayedTransition(binding.layoutDialog)
            }
            isDialogOpened = true

        }

        binding.layoutBackground.setOnClickListener {
            ConstraintSet().apply {
                clone(this@TestActivity, R.layout.dialog_contraint_close)
                applyTo(binding.layoutDialog)
                TransitionManager.beginDelayedTransition(binding.layoutDialog)
            }
            isDialogOpened = false
        }

    }

    override fun onBackPressed() {
        if (isDialogOpened) {
            ConstraintSet().apply {
                clone(this@TestActivity, R.layout.dialog_contraint_close)
                applyTo(binding.layoutDialog)
                TransitionManager.beginDelayedTransition(binding.layoutDialog)
            }
            isDialogOpened = false
        } else {
            super.onBackPressed()
        }
    }

}
