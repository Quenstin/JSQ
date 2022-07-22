package com.example.calculator.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils
import com.example.calculator.R
import com.example.calculator.manager.ConvertResult

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：ln口
 */
class LnView constructor(context: Context, level: Int) : FormulaView(context, level) {

    private lateinit var lnRoot:LinearLayout
    private lateinit var lnHorizontalRoot:LinearLayout

    init {
        initView()
    }

    /**
     * 初始化布局和View
     */
    private fun initView() {
        LogUtils.d("当前级别---$level")
        when (level) {
            1 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_ln_1,this)
            }
            2 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_ln_2,this)

            }
            else -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_ln_3,this)

            }
        }

        lnRoot=findViewById(R.id.ln_root) //根布局
        lnHorizontalRoot=findViewById(R.id.ln_horizontal_root) //输入布局

        setCanClickView(lnRoot,false,true)
        setCanClickView(lnHorizontalRoot,true,false)

    }

    override fun isSpecial(clickName: String?): Boolean {
        if (clickName.equals(resources.getResourceEntryName(lnHorizontalRoot.id))){
            return true
        }
        return false
    }

    override fun toLatexString(convertResult: ConvertResult?) {
        convertResult?.let {
            it.message+="ln("
            toLatexString(lnHorizontalRoot,it)
            it.message+=")"
            if (!it.isSuccess){
                return@let
            }
        }
    }
}