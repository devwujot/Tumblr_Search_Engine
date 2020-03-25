package com.devwujot.tumblrsearch.framework.di.builder

import com.devwujot.tumblrsearch.presentation.activity.DetailActivity
import com.devwujot.tumblrsearch.presentation.activity.SearchActivity
import com.devwujot.tumblrsearch.presentation.activity.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity
}