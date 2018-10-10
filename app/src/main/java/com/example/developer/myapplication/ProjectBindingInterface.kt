package com.example.developer.myapplication

import android.databinding.DataBindingComponent
import javax.inject.Inject

class ProjectBindingInterface @Inject constructor(
        private val adapter: BindingAdapterProject
) : DataBindingComponent {

    override fun getBindingAdapterProject(): BindingAdapterProject {
        return adapter
    }
}