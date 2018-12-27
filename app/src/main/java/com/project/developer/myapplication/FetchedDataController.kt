package com.project.developer.myapplication

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchedDataController @Inject constructor(
        private val repository: TestRepository,
        private val disposableContainer: CompositeDisposable
) : PageKeyedDataSource<Int, String>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, String>) {
        repository.getItems(1)
                .doOnSubscribe { disposableContainer.add(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ callback.onResult(it, null, 2) }, Throwable::printStackTrace)
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        repository.getItems(params.key)
                .doOnSubscribe { disposableContainer.add(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it, if (it.isEmpty()) null else params.key + 1)
                }, Throwable::printStackTrace)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, String>) = Unit

}