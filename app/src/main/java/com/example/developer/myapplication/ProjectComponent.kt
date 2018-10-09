package com.example.developer.myapplication

import android.app.Application
import android.databinding.DataBindingComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BindingModule::class])
interface ProjectComponent : DataBindingComponent {

    @Component.Builder
    interface Builder {

        fun build(): DataBindingComponent

        @BindsInstance
        fun application(application: Application): Builder

    }

}