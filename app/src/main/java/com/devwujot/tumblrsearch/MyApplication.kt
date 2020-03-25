package com.devwujot.tumblrsearch

import android.os.StrictMode
import androidx.databinding.DataBindingUtil
import com.devwujot.tumblrsearch.framework.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class MyApplication: DaggerApplication() {

    private val applicationInjector = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    public override fun applicationInjector() = applicationInjector

    override fun onCreate() {
        super.onCreate()

        setupStrictMode()
        setupDagger()
    }

    private fun setupDagger() {
        DataBindingUtil.setDefaultComponent(
            applicationInjector
                .bindingComponentBuilder()
                .build()
        )
    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }
}