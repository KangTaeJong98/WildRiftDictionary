package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.Spell
import com.taetae98.wildriftdictionary.databinding.HolderSpellBinding

class SpellAdapter : BaseAdapter<Spell>(SpellItemCallback()) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Spell> {
        return SpellHolder(HolderSpellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class SpellHolder(binding: HolderSpellBinding) : BaseHolder<HolderSpellBinding, Spell>(binding) {
        override fun bind(element: Spell) {
            super.bind(element)
            binding.spell = element
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).name.hashCode().toLong()
    }

    class SpellItemCallback : DiffUtil.ItemCallback<Spell>() {
        override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
            return oldItem == newItem
        }
    }
}