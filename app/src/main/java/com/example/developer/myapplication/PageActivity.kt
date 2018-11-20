package com.example.developer.myapplication

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.developer.myapplication.databinding.ActivityPageBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class PageActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: PageViewModel

    @Inject
    lateinit var bindingInterface: ProjectBindingInterface

    private lateinit var binding: ActivityPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_page, bindingInterface)
    }


}