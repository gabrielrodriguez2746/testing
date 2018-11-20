package com.example.developer.myapplication

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class ProjectListAdapter<T : RecyclerItem<*>, S : ProjectRecyclerView<*>>(
        private val factory: ViewFactory<S>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(RecyclerItemDiffCallback<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(factory.getView(parent, viewType))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView as ProjectRecyclerView<Any>
        getItem(position)?.let {
            view.bind(it.getContent(), position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getType() ?: -1
    }
}

interface RecyclerItem<R : Any> {

    fun getType(): Int

    fun getId(): Int

    fun getContent(): R

    fun getComparator(): Any
}

interface ProjectRecyclerView<R: Any> {

    fun bind(item: R, position: Int)

}

class RecyclerItemDiffCallback<T : RecyclerItem<*>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(firsteItem: T, secondItem: T): Boolean {
        return firsteItem.getId() == secondItem.getId()
    }

    override fun areContentsTheSame(firsteItem: T, secondItem: T): Boolean {
        return firsteItem.getComparator() == secondItem.getComparator()
    }

}

abstract class ViewFactory<S : ProjectRecyclerView<*>> {

    abstract fun getView(parent: ViewGroup, viewType: Int): S
}

private class ViewHolder<S : ProjectRecyclerView<*>>(view: S) :
        RecyclerView.ViewHolder(view as View)