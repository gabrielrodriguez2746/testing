package com.project.developer.myapplication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import javax.inject.Inject

class PagingBindingAdapterProject @Inject constructor(
        private val items: LiveData<PagedList<RecyclerItem<Any>>>
) {

    private val adapter = ProjectListAdapter<RecyclerItem<Any>, ProjectRecyclerView<*>>(
            object : ViewFactory<ProjectRecyclerView<*>>() {
                override fun getView(parent: ViewGroup, viewType: Int): ProjectRecyclerView<*> {
                    return CustomView(parent.context).apply {
                        layoutParams = ViewGroup.MarginLayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        gravity = Gravity.CENTER
                    }
                }

            }
    )

    @BindingAdapter("on_configure")
    fun RecyclerView.configure(configure: Boolean) {
        if (configure) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PagingBindingAdapterProject.adapter
            items.observe(context as LifecycleOwner, Observer {
                it?.let { list ->
                    this@PagingBindingAdapterProject.adapter.submitList(list)
                }
            })
        }
    }

    class CustomView(context: Context) : TextView(context), ProjectRecyclerView<String> {

        override fun bind(item: String, position: Int) {
            text = item
        }

    }
}