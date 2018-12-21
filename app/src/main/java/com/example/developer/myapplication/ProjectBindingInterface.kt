package com.example.developer.myapplication

import androidx.databinding.DataBindingComponent
import javax.inject.Inject

class ProjectBindingInterface @Inject constructor(
        private val adapter: BindingAdapterProject,
        private val pagingAdapter: PagingBindingAdapterProject
) : DataBindingComponent {

    override fun getPagingBindingAdapterProject(): PagingBindingAdapterProject {
        return pagingAdapter
    }

    override fun getBindingAdapterProject(): BindingAdapterProject {
        return adapter
    }
}