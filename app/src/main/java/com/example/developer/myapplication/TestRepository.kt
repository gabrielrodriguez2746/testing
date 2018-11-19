package com.example.developer.myapplication

import io.reactivex.Single
import javax.inject.Inject

class TestRepository @Inject constructor() {

    fun getItems(index: Int) : Single<List<String>> {
        return if (index == 3) {
            Single.just(emptyList())
        } else {
            Single.just(listOf("A", "B", "C", "D", "E"))
        }
    }
}