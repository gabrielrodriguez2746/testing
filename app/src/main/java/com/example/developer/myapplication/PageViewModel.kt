package com.example.developer.myapplication

import androidx.lifecycle.LifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PageViewModel @Inject constructor(
        private val disposable: CompositeDisposable
) : LifecycleObserver {

    fun onDestroy() {
        disposable.clear()
    }


}