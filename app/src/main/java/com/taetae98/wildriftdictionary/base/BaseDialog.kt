package com.taetae98.wildriftdictionary.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDialog<VB: ViewDataBinding>(context: Context, private val layoutId: Int) : Dialog(context) {
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(binding.root)
    }

    protected open fun init() {
        initDataBinding()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
    }
}