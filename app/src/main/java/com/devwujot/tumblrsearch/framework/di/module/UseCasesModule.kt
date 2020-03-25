package com.devwujot.tumblrsearch.framework.di.module

import com.devwujot.tumblrsearch.core.repository.TumblrRepository
import com.devwujot.tumblrsearch.core.useCase.GetPostsByUsername
import com.devwujot.tumblrsearch.framework.useCases.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideUseCases(repository: TumblrRepository) = UseCases(
        GetPostsByUsername(repository)
    )
}