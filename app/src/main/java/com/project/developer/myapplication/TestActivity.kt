package com.project.developer.myapplication

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import com.project.developer.myapplication.databinding.ActivityTestBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class TestActivity : AppCompatActivity() {

    private var isDialogOpened = false

    private lateinit var binding: ActivityTestBinding

    private val viewModel by lazy {
        TestViewModel()
    }

    @Inject
    lateinit var bindingInterface: ProjectBindingInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test, bindingInterface)
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
