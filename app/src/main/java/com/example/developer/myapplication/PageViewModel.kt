package com.example.developer.myapplication

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PageViewModel @Inject constructor(
        val items: LiveData<PagedList<RecyclerItem<Any>>>,
        private val disposable: CompositeDisposable
) : LifecycleObserver {

    fun onDestroy() {
        disposable.clear()
    }


}