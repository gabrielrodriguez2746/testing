package com.example.developer.myapplication

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class TestViewModel : LifecycleObserver {

    private val disposable = CompositeDisposable()

    val onDialogContraintChange = ObservableBoolean()
    val itemsClickedSubject = PublishSubject.create<Int>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        disposable.add(itemsClickedSubject.hide().subscribe {
            val constraintChange = onDialogContraintChange.get()
            onDialogContraintChange.set(!constraintChange)
        })
    }

}