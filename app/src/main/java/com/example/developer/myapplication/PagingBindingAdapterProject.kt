package com.example.developer.myapplication

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Context
import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import javax.inject.Inject

class PagingBindingAdapterProject @Inject constructor() {

    private val adapter = ProjectListAdapter<RecyclerItem<Any>, ProjectRecyclerView<*>>(
            object : ViewFactory<ProjectRecyclerView<*>>() {
                override fun getView(parent: ViewGroup, viewType: Int): ProjectRecyclerView<*> {
                    return CustomView(parent.context)
                }

            }
    )

    @BindingAdapter("on_configure", "items")
    fun RecyclerView.configure(configure: Boolean, items: LiveData<PagedList<RecyclerItem<Any>>>) {
        if (configure) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PagingBindingAdapterProject.adapter
        }
        items.observe(context as LifecycleOwner, Observer {
            it?.let { list ->
                this@PagingBindingAdapterProject.adapter.submitList(list)
            }
        })
    }

    class CustomView(context: Context) : TextView(context), ProjectRecyclerView<String> {

        override fun bind(item: String, position: Int) {
            text = item
        }

    }
}