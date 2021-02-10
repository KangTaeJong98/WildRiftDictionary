package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.Rune
import com.taetae98.wildriftdictionary.databinding.HolderRuneBinding

class RuneAdapter : BaseAdapter<Rune>(RuneItemCallback()) {
    init {
        setHasStableIds(true)
    }

    var onClick: ((rune: Rune) -> Unit)? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Rune> {
        return RuneHolder(HolderRuneBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class RuneHolder(binding: HolderRuneBinding) : BaseHolder<HolderRuneBinding, Rune>(binding) {
        init {
            binding.setOnClick {
                onClick?.invoke(element)
            }
        }

        override fun bind(element: Rune) {
            super.bind(element)
            binding.rune = element
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).name.hashCode().toLong()
    }

    class RuneItemCallback : DiffUtil.ItemCallback<Rune>() {
        override fun areItemsTheSame(oldItem: Rune, newItem: Rune): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Rune, newItem: Rune): Boolean {
            return oldItem == newItem
        }
    }
}