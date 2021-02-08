package com.taetae98.wildriftdictionary.dialog

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.Skill
import com.taetae98.wildriftdictionary.databinding.DialogSkillBinding

class SkillDialog(context: Context, private var skill: Skill) : BaseDialog<DialogSkillBinding>(context, R.layout.dialog_skill) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun bind(skill: Skill) {
        this.skill = skill
        initSkill()
    }

    override fun init() {
        super.init()
        initSkill()
    }

    private fun initSkill() {
        binding.skill = skill
    }
}