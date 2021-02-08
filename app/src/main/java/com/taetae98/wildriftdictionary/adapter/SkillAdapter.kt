package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.data.Skill
import com.taetae98.wildriftdictionary.databinding.HolderSkillBinding
import com.taetae98.wildriftdictionary.dialog.SkillDialog

class SkillAdapter : BaseAdapter<Skill>(SkillItemCallback()) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Skill> {
        return SkillHolder(HolderSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).name.hashCode().toLong()
    }

    class SkillHolder(binding: HolderSkillBinding) : BaseHolder<HolderSkillBinding, Skill>(binding) {
        init {
            binding.setOnClick {
                SkillDialog(context, element).show()
            }
        }
        override fun bind(element: Skill) {
            super.bind(element)
            binding.skill = element
        }
    }

    class SkillItemCallback : DiffUtil.ItemCallback<Skill>() {
        override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
            return oldItem == newItem
        }
    }
}