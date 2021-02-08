package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.Champion
import com.taetae98.wildriftdictionary.databinding.HolderAbilityBinding

class AbilityAdapter : BaseAdapter<Champion.Ability>(AbilityItemCallback()) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Champion.Ability> {
        return AbilityHolder(HolderAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class AbilityHolder(binding: HolderAbilityBinding) : BaseHolder<HolderAbilityBinding, Champion.Ability>(binding) {
        override fun bind(element: Champion.Ability) {
            super.bind(element)
            binding.ability = element
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).key.hashCode().toLong()
    }

    class AbilityItemCallback : DiffUtil.ItemCallback<Champion.Ability>() {
        override fun areItemsTheSame(oldItem: Champion.Ability, newItem: Champion.Ability): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: Champion.Ability, newItem: Champion.Ability): Boolean {
            return oldItem == newItem
        }
    }
}