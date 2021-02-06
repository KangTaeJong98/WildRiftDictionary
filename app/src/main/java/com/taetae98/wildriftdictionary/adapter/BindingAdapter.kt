package com.taetae98.wildriftdictionary.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageURL")
    fun setImageURL(view: ImageView, url: String?) {
        Glide.with(view).load(url).into(view)
    }
}