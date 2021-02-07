package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.Champion
import com.taetae98.wildriftdictionary.databinding.HolderAbilityBinding
import com.taetae98.wildriftdictionary.databinding.HolderSubAbilityBinding

class AbilityAdapter : BaseAdapter<Champion.Ability>(AbilityItemCallback()) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Champion.Ability> {
        return when(viewType) {
            R.layout.holder_ability -> {
                AbilityHolder(HolderAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            R.layout.holder_sub_ability -> {
                SubAbilityHolder(HolderSubAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    inner class AbilityHolder(binding: HolderAbilityBinding) : BaseHolder<HolderAbilityBinding, Champion.Ability>(binding) {
        override fun bind(element: Champion.Ability) {
            super.bind(element)
            binding.ability = element
        }
    }

    inner class SubAbilityHolder(binding: HolderSubAbilityBinding) : BaseHolder<HolderSubAbilityBinding, Champion.Ability>(binding) {
        override fun bind(element: Champion.Ability) {
            super.bind(element)
            binding.ability = element
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position).type) {
            Champion.Ability.Type.NORMAL -> {
                R.layout.holder_ability
            }
            Champion.Ability.Type.SUB -> {
                R.layout.holder_sub_ability
            }
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