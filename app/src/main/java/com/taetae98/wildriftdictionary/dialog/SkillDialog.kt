package com.taetae98.wildriftdictionary.dialog

import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.databinding.DialogSkillBinding

class SkillDialog : BaseDialog<DialogSkillBinding>(R.layout.dialog_skill) {
    private val args by navArgs<SkillDialogArgs>()

    override fun onResume() {
        super.onResume()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun init() {
        super.init()
        initSkill()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun initSkill() {
        binding.skill = args.skill
    }
}