package com.taetae98.wildriftdictionary.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.data.Skill

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageURL")
    fun setImageURL(view: ImageView, url: String?) {
        Glide.with(view).load(url).into(view)
    }

    @JvmStatic
    @BindingAdapter("costType")
    fun setSkillCost(view: TextView, type: Skill.CostType?) {
        when(type) {
            Skill.CostType.ENERGY -> {
                view.setTextColor(view.context.resources.getColor(R.color.color_energy, null))
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(ResourcesCompat.getDrawable(view.context.resources, R.drawable.ic_energy, null), null, null, null)
            }
            Skill.CostType.HP -> {
                view.setTextColor(view.context.resources.getColor(R.color.color_hp, null))
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(ResourcesCompat.getDrawable(view.context.resources, R.drawable.ic_hp, null), null, null, null)
            }
            Skill.CostType.MP -> {
                view.setTextColor(view.context.resources.getColor(R.color.color_mp, null))
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(ResourcesCompat.getDrawable(view.context.resources, R.drawable.ic_mp, null), null, null, null)
            }
            Skill.CostType.ANGER -> {
                view.setTextColor(view.context.resources.getColor(R.color.color_anger, null))
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(ResourcesCompat.getDrawable(view.context.resources, R.drawable.ic_anger, null), null, null, null)
            }
            else -> {

            }
        }
    }
}