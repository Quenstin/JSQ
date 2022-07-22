package com.example.calculator.widget

import android.content.Context
import android.view.LayoutInflater
import com.blankj.utilcode.util.LogUtils
import com.example.calculator.R
import com.example.calculator.databinding.LayoutLatexMod1Binding
import com.example.calculator.manager.ConvertResult

/**
 * Created by hdyjzgq
 * data on 2021/3/16
 * function is ：取模
 */
class ModView constructor(context: Context, level: Int) : FormulaView(context, level) {

    private val mViewBinding: LayoutLatexMod1Binding =
        LayoutLatexMod1Binding.inflate(LayoutInflater.from(context), this, true)

    init {
        initView()
    }

    private fun initView() {
        LogUtils.d("当前级别--------$level")
        LayoutInflater.from(context).inflate(R.layout.layout_latex_mod_1, this)
        setCanClickView(mViewBinding.modRoot, false, true)
        setCanClickView(mViewBinding.modHorizontalRoot0, true, false)
        setCanClickView(mViewBinding.modHorizontalRoot, false, false)

    }

    override fun isSpecial(clickName: String?): Boolean {
        return true
    }

    override fun toLatexString(convertResult: ConvertResult?) {
        convertResult?.let {
            it.message += "mod("
            toLatexString(mViewBinding.modHorizontalRoot0, it)
            it.message += ","
            toLatexString(mViewBinding.modHorizontalRoot, it)
            it.message += ")"
        }
    }
}