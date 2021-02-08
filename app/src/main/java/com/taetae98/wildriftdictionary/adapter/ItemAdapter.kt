package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.Item
import com.taetae98.wildriftdictionary.databinding.HolderItemBinding
import com.taetae98.wildriftdictionary.dialog.ItemDialog

class ItemAdapter : BaseAdapter<Item>(ItemItemCallback()) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Item> {
        return ItemHolder(HolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ItemHolder(binding: HolderItemBinding) : BaseHolder<HolderItemBinding, Item>(binding) {
        init {
            binding.setOnClick {
                ItemDialog(context, element).show()
            }
        }
        override fun bind(element: Item) {
            super.bind(element)
            binding.item = element
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).name.hashCode().toLong()
    }

    class ItemItemCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}