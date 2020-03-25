package com.devwujot.tumblrsearch.framework.di.module

import com.devwujot.tumblrsearch.core.repository.TumblrRepository
import com.devwujot.tumblrsearch.framework.data.RepositoryDataSource
import com.devwujot.tumblrsearch.framework.data.remote.TumblrApiService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(remoteSource: TumblrApiService) =
        TumblrRepository(RepositoryDataSource(remoteSource))
}