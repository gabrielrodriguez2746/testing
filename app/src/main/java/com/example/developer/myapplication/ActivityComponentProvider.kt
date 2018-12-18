package com.example.developer.myapplication

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityComponentProvider {

    @ContributesAndroidInjector
    abstract fun bindActivity(): TestActivity

    @ContributesAndroidInjector(modules = [FragmentComponentProvider::class])
    abstract fun bindPgingACtivity(): PageActivity

}


@Module
abstract class FragmentComponentProvider {

    @ContributesAndroidInjector
    abstract fun bindFragment(): MenuFragment

}