package com.devwujot.tumblrsearch.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.devwujot.tumblrsearch.R
import com.devwujot.tumblrsearch.core.data.Post
import com.devwujot.tumblrsearch.databinding.ActivityDetailBinding
import com.devwujot.tumblrsearch.framework.viewModel.DetailActivityViewModel
import com.devwujot.tumblrsearch.presentation.utility.INTENT_POST_KEY
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailActivityViewModel by viewModels { vmFactory }

    companion object {
        fun newIntent(context: Context, post: Post): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(INTENT_POST_KEY, post)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.viewModel = viewModel

        initToolbar()
        receivePost()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.empty_string)
        }
    }

    private fun receivePost() {
        val post: Post = intent.getParcelableExtra(INTENT_POST_KEY) as Post
        viewModel.assignPost(post)
    }
}
