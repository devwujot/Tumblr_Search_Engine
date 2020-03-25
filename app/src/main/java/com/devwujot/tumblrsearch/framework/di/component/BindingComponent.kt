package com.devwujot.tumblrsearch.framework.di.component

import androidx.databinding.DataBindingComponent
import com.devwujot.tumblrsearch.framework.di.module.BindingModule
import com.devwujot.tumblrsearch.framework.di.scope.DataBindingScoped
import dagger.Subcomponent

@DataBindingScoped
@Subcomponent(modules = [BindingModule::class])
interface BindingComponent : DataBindingComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): BindingComponent
    }

}