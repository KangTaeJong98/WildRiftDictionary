package com.taetae98.wildriftdictionary.dialog

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.Spell
import com.taetae98.wildriftdictionary.databinding.DialogSpellBinding

class SpellDialog(context: Context, private var spell: Spell = Spell()) : BaseDialog<DialogSpellBinding>(context, R.layout.dialog_spell) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun bind(spell: Spell) {
        this.spell = spell
        initSpell()
    }

    override fun init() {
        super.init()
        initSpell()
    }

    private fun initSpell() {
        binding.spell = spell
    }
}