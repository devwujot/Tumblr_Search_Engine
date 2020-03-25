package com.devwujot.tumblrsearch.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devwujot.tumblrsearch.R
import com.devwujot.tumblrsearch.core.data.Post
import com.devwujot.tumblrsearch.databinding.ItemPostBinding
import com.devwujot.tumblrsearch.presentation.listener.ItemPostListener

class PostsAdapter(val posts: ArrayList<Post>) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>(), ItemPostListener {

    lateinit var onItemClickListener: (Post) -> Unit

    fun updatePostList(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemPostBinding>(
            inflater,
            R.layout.item_post,
            parent,
            false
        )
        return PostViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.view.post = posts[position]
        holder.view.listener = this
    }

    override fun onLayoutClicked(v: View) {
        for (item in posts) {
            if (v.tag == item.id) {
                onItemClickListener.invoke(item)
            }
        }
    }

    class PostViewHolder(var view: ItemPostBinding) : RecyclerView.ViewHolder(view.root)
}