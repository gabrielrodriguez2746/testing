package com.project.developer.myapplication

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityComponentProvider::class,
    BindingModule::class,
    FetchDataDependencies::class
])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: MyApplication): Builder

    }

}