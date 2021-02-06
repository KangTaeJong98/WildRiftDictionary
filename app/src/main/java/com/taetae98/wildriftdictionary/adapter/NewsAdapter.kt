package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.News
import com.taetae98.wildriftdictionary.databinding.HolderNewsBinding
import com.taetae98.wildriftdictionary.fragment.MainFragment
import com.taetae98.wildriftdictionary.fragment.MainFragmentDirections

class NewsAdapter : BaseAdapter<News>(NewsItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, News> {
        return SpecialNewsHolder(HolderNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.holder_news
    }

    inner class SpecialNewsHolder(binding: HolderNewsBinding) : BaseHolder<HolderNewsBinding, News>(binding) {
        init {
            binding.setOnClick {
                if (it.findFragment<Fragment>() is MainFragment) {
                    it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToWebViewFragment(element.url))
                }
            }
        }

        override fun bind(element: News) {
            super.bind(element)
            binding.news = element
        }
    }

    class NewsItemCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
}