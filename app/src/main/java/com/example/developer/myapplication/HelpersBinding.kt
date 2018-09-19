package com.example.developer.myapplication

import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v7.widget.Toolbar
import io.reactivex.subjects.PublishSubject

@BindingAdapter(value = ["bind:navigation_icon", "bind:navigation_color"], requireAll = false)
fun Toolbar.onConfigToolbar(icon: Drawable, @ColorInt color: Int? = null) {
    navigationIcon = color?.let {
        val mutatedDrawable = icon.mutate()
        mutatedDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        mutatedDrawable
    } ?: icon
}

@BindingAdapter("bind:on_navigationClicked")
fun Toolbar.setOnNavigationListener(listener: PublishSubject<Int>) {
    setNavigationOnClickListener {
        listener.onNext(id)
    }
}