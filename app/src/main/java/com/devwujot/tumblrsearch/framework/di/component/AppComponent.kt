package com.devwujot.tumblrsearch.framework.di.component

import android.app.Application
import com.devwujot.tumblrsearch.MyApplication
import com.devwujot.tumblrsearch.framework.di.builder.ActivityBuilder
import com.devwujot.tumblrsearch.framework.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCasesModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun bindingComponentBuilder(): BindingComponent.Builder
}