package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.Champion
import com.taetae98.wildriftdictionary.databinding.HolderChampionBinding

class ChampionAdapter : BaseAdapter<Champion>(ChampionItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Champion> {
        return ChampionHolder(HolderChampionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.holder_champion
    }

    inner class ChampionHolder(binding: HolderChampionBinding) : BaseHolder<HolderChampionBinding, Champion>(binding) {
        init {

        }

        override fun bind(element: Champion) {
            super.bind(element)
            binding.champion = element
        }
    }

    class ChampionItemCallback : DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            return oldItem.nameEn == newItem.nameEn
        }

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            return oldItem == newItem
        }
    }
}