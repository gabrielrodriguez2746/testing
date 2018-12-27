package com.project.developer.myapplication

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import javax.inject.Inject

class FetchDataFactory @Inject constructor(
        private val dataController: PageKeyedDataSource<Int, String>
) : DataSource.Factory<Int, RecyclerItem<Any>>() {


    override fun create(): DataSource<Int, RecyclerItem<Any>> {
        return dataController.map {
            object : RecyclerItem<Any> {
                override fun getType(): Int = 0
                override fun getId(): Int = it.hashCode()
                override fun getContent(): String = it
                override fun getComparator(): Any = it

            }
        }
    }
}