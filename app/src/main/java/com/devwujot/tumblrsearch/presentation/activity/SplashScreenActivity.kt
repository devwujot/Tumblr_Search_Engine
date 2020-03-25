package com.devwujot.tumblrsearch.presentation.activity

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devwujot.tumblrsearch.R
import com.devwujot.tumblrsearch.databinding.ActivitySplashScreenBinding
import com.devwujot.tumblrsearch.framework.viewModel.SplashScreenActivityViewModel
import com.devwujot.tumblrsearch.presentation.utility.SPLASH_SCREEN_DELAY
import com.devwujot.tumblrsearch.presentation.utility.reObserve
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashScreenActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: SplashScreenActivityViewModel by viewModels { vmFactory }

    private val goToSearchViewObserver = Observer<Boolean> { isGoing ->
        if (isGoing) {
            goToSearchView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding.viewModel = viewModel

        with(viewModel) {
            goToSearchViewObservable.reObserve(this@SplashScreenActivity, goToSearchViewObserver)
        }

        delaySplashScreen()
    }

    private fun delaySplashScreen() {
        Handler().postDelayed({
            viewModel.goToSearchView()
        }, SPLASH_SCREEN_DELAY)
    }

    private fun goToSearchView() {
        startActivity(SearchActivity.newIntent(this))
        finish()
    }
}