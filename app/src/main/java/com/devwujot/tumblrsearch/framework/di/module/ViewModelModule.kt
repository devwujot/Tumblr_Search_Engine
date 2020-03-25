package com.devwujot.tumblrsearch.framework.di.module

import androidx.lifecycle.ViewModel
import com.devwujot.tumblrsearch.framework.di.vm.ViewModelKey
import com.devwujot.tumblrsearch.framework.useCases.UseCases
import com.devwujot.tumblrsearch.framework.viewModel.DetailActivityViewModel
import com.devwujot.tumblrsearch.framework.viewModel.SearchActivityViewModel
import com.devwujot.tumblrsearch.framework.viewModel.SplashScreenActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(SearchActivityViewModel::class)
    fun searchActivityViewModel(useCases: UseCases): ViewModel = SearchActivityViewModel(useCases)

    @Provides
    @IntoMap
    @ViewModelKey(SplashScreenActivityViewModel::class)
    fun splashScreenActivityViewModel(): ViewModel = SplashScreenActivityViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    fun detailActivityViewModel(): ViewModel = DetailActivityViewModel()
}