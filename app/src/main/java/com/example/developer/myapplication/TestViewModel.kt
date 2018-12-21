package com.example.developer.myapplication

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.databinding.ObservableBoolean
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