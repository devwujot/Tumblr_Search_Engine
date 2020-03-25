package com.devwujot.tumblrsearch.presentation.utility

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.devwujot.tumblrsearch.R

@BindingAdapter("loadImage")
fun ImageView.loadUrl(url: String?) {
    url?.let { link ->
        context?.let {
            val options = RequestOptions()
                .placeholder(progressDrawable(context))
                .error(R.drawable.error_image)
            Glide.with(context.applicationContext)
                .load(link)
                .apply(options)
                .into(this)
        }
    }
}

fun progressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = PROGRESS_DRAWABLE_STROKE_WIDTH
        centerRadius = PROGRESS_DRAWABLE_CENTER_RADIUS
        start()
    }
}

@BindingAdapter("formatNumber")
fun TextView.formatNumber(number: String) {
    val num = String.format("%,d", number.toLong())
    text = "$num notes"
}