package com.example.calculator.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.calculator.R
import com.example.calculator.manager.ConvertResult

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：定积分
 */
class IntegView constructor(context: Context, level: Int) : FormulaView(context, level) {
    private lateinit var integRoot:RelativeLayout
    private lateinit var integUpRoot:LinearLayout
    private lateinit var integDownRoot:LinearLayout
    private lateinit var integRightRoot:LinearLayout


    init {
        initView()
    }

    /**
     * 初始化
     */
    private fun initView() {
        when (level) {
            1 -> {
            LayoutInflater.from(context).inflate(R.layout.layout_latex_integ_1,this)
            }
            2 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_integ_2,this)
            }
            else -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_integ_3,this)
            }
        }

        integRoot=findViewById(R.id.integ_root)
        integUpRoot=findViewById(R.id.integ_up_root)
        integDownRoot=findViewById(R.id.integ_down_root)
        integRightRoot=findViewById(R.id.integ_right_root)

        setCanClickView(integRoot,false,true)
        setCanClickView(integUpRoot,true,false)
        setCanClickView(integDownRoot,false,false)
        setCanClickView(integRightRoot,false,false)

    }

    override fun isSpecial(clickName: String?): Boolean {
        return true
    }

    override fun toLatexString(convertResult: ConvertResult?) {
        convertResult?.let {
            it.message+="integ("
            toLatexString(integUpRoot,convertResult)
            it.message+=","

            toLatexString(integDownRoot,convertResult)
            it.message+=","

            toLatexString(integRightRoot,convertResult)
            it.message+=")"


        }
    }
}