package com.example.developer.myapplication

import android.app.Activity
import android.app.Application
import android.databinding.DataBindingComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    private lateinit var bindingComponent: DataBindingComponent

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        bindingComponent = DaggerAppComponent.builder()
                .application(this).build()
                .apply { inject(this@MyApplication) }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    fun getComponent() = bindingComponent
}