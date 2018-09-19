package com.example.developer.myapplication

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.developer.myapplication.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = TestViewModel()
    }

}
