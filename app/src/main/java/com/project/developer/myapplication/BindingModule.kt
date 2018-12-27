package com.project.developer.myapplication

import dagger.Module
import dagger.Provides

@Module
object BindingModule {

    @Provides
    @Copy
    @JvmStatic
    fun provideCopy(): String {
        return "RappiRappi"
    }

}