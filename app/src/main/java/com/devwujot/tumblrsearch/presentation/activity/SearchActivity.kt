package com.devwujot.tumblrsearch.presentation.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.devwujot.tumblrsearch.R
import com.devwujot.tumblrsearch.core.data.Post
import com.devwujot.tumblrsearch.core.data.Resource
import com.devwujot.tumblrsearch.databinding.ActivitySearchBinding
import com.devwujot.tumblrsearch.framework.viewModel.SearchActivityViewModel
import com.devwujot.tumblrsearch.presentation.adapter.PostsAdapter
import com.devwujot.tumblrsearch.presentation.utility.RECYCLER_VIEW_COLUMNS_NUMBER
import com.devwujot.tumblrsearch.presentation.utility.reObserve
import com.devwujot.tumblrsearch.presentation.utility.showToast
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchActivityViewModel by viewModels { vmFactory }
    private lateinit var postsAdapter: PostsAdapter

    companion object {
        fun newIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

    private val postResponseObserver = Observer<Resource<*>> { postResponse ->
        when (postResponse.status) {
            Resource.Status.SUCCESS -> {
                val posts = postResponse.data as List<Post>
                postsAdapter.updatePostList(posts)
                with(binding) {
                    emptyLayout.visibility = View.GONE
                    searchProgressLayout.visibility = View.GONE
                    postList.visibility = View.VISIBLE
                }
            }
            Resource.Status.ERROR -> {
                showToast(postResponse.errorMessage.toString())
                with(binding) {
                    emptyLayout.visibility = View.VISIBLE
                    searchProgressLayout.visibility = View.GONE
                }
            }
            Resource.Status.LOADING -> {
                with(binding) {
                    searchProgressLayout.visibility = View.VISIBLE
                    postList.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel

        with(viewModel) {
            postsResponse.reObserve(this@SearchActivity, postResponseObserver)
        }

        initToolbar()
        initPostAdapter()
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            executePendingBindings()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        with(binding) {
            viewModel = null
            executePendingBindings()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as SearchView
        with(searchView) {
            setSearchableInfo(manager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    with(searchView) {
                        clearFocus()
                        setQuery(resources.getString(R.string.empty_string), false)
                    }
                    searchItem.collapseActionView()
                    query?.let {
                        viewModel.getPostsByUsername(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = resources.getString(R.string.search_activity_title)
        }
    }

    private fun initPostAdapter() {
        postsAdapter = PostsAdapter(arrayListOf())
        binding.postList.apply {
            layoutManager = GridLayoutManager(context, RECYCLER_VIEW_COLUMNS_NUMBER)
            adapter = postsAdapter
        }
        postsAdapter.onItemClickListener = { item ->
            startActivity(DetailActivity.newIntent(this, item))
        }
    }
}