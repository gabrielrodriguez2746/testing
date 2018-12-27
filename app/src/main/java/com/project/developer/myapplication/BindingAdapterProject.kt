package com.project.developer.myapplication

import androidx.databinding.BindingAdapter
import android.widget.TextView
import javax.inject.Inject

class BindingAdapterProject @Inject constructor(@Copy private val copy: String) {

    @BindingAdapter("something")
    fun TextView.setSomething(hasSomething: Boolean) {
        if (hasSomething) {
            text = copy
        }
    }
}