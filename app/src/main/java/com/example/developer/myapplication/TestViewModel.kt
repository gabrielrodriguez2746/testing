package com.example.developer.myapplication

import io.reactivex.subjects.PublishSubject

class TestViewModel {

    val itemsClickedSubject = PublishSubject.create<Int>()

}