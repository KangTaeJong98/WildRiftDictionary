package com.taetae98.wildriftdictionary.dialog

import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.databinding.DialogSpellBinding

class SpellDialog : BaseDialog<DialogSpellBinding>(R.layout.dialog_spell) {
    private val args by navArgs<SpellDialogArgs>()

    override fun onResume() {
        super.onResume()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun init() {
        super.init()
        initSpell()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun initSpell() {
        binding.spell = args.spell
    }
}