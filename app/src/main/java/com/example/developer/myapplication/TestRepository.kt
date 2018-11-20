package com.example.developer.myapplication

import io.reactivex.Single
import javax.inject.Inject

class TestRepository @Inject constructor() {

    fun getItems(index: Int) : Single<List<String>> {
        return if (index == 20) {
            Single.just(emptyList())
        } else {
            Single.just(listOf("ANABEL", "BRAULIO", "CARLOS", "DIEGO", "ESTEBAN"))
        }
    }
}