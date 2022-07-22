package com.example.calculator.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.manager.ConvertResult

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：公倍数 or 公约数
 * str:接收 lcm or gcd
 */
class LcmGcdView(context: Context, level: Int, str: String) : FormulaView(context, level) {
    private lateinit var lcmGcdRoot: LinearLayout
    private lateinit var lcmGcdLeftRoot: LinearLayout
    private lateinit var lcmGcdRightRoot: LinearLayout
    private var mType: String

    init {
        initView(str)
        mType = str
    }

    /**
     * 初始化view
     */
    private fun initView(str: String) {
        when (level) {
            1 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_lcmgcd_1, this)
            }
            2 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_lcmgcd_2, this)

            }
            else -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_lcmgcd_3, this)

            }
        }
        lcmGcdRoot = findViewById(R.id.lcm_gcd_root)
        lcmGcdLeftRoot = findViewById(R.id.lcm_gcd_left_root)
        lcmGcdRightRoot = findViewById(R.id.lcm_gcd_right_root)
        val tContent = findViewById<TextView>(R.id.lcm_gcd_text)
        tContent.text = str

        setCanClickView(lcmGcdRoot, false, true)
        setCanClickView(lcmGcdLeftRoot, true, false)
        setCanClickView(lcmGcdRightRoot, false, false)

    }

    override fun isSpecial(clickName: String?): Boolean {
        return true
    }

    override fun toLatexString(convertResult: ConvertResult?) {
        if (mType == "lcm") {
            getLatexStr(convertResult)

        } else {
            getLatexStr(convertResult)

        }

    }

    private fun getLatexStr(convertResult: ConvertResult?) {
        convertResult?.let {
            it.message += "$mType("
            toLatexString(lcmGcdLeftRoot, it)
            it.message += ","
            toLatexString(lcmGcdRightRoot, it)
            it.message += ")"

            if (!it.isSuccess) {
                return@let
            }
        }
    }
}