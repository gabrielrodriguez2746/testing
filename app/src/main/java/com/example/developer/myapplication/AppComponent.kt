package com.example.developer.myapplication

import android.databinding.DataBindingComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityComponentProvider::class, BindingModule::class, AppModule::class])
interface AppComponent : DataBindingComponent, AndroidInjector<MyApplication> {

    override fun getBindingAdapterProject(): BindingAdapterProject {
        return getBinding()
    }

    fun getBinding(): BindingAdapterProject

//    fun getBindingComponent(): DataBindingComponent

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: MyApplication): Builder

    }

}