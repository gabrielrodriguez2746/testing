package com.project.developer.myapplication

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class FetchDataDependencies {

    @Binds
    abstract fun bindDataFactory(factory: FetchDataFactory): DataSource.Factory<Int, RecyclerItem<Any>>

    @Binds
    abstract fun bindDataController(controller: FetchedDataController): PageKeyedDataSource<Int, String>

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideDisposableContainer(): CompositeDisposable {
            return CompositeDisposable()
        }

        @JvmStatic
        @Provides
        fun provideConfiguration(): PagedList.Config {
            return PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(5)
                    .build()
        }

        @JvmStatic
        @Provides
        fun provideLiveData(configuration: PagedList.Config,
                            factory: DataSource.Factory<Int, RecyclerItem<Any>>): LiveData<PagedList<RecyclerItem<Any>>> {
            return LivePagedListBuilder<Int, RecyclerItem<Any>>(factory, configuration).build()
        }

    }

}