package com.example.calculator.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.calculator.R
import com.example.calculator.manager.ConvertResult

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：A吕 C吕符号
 * type接受传值 A or C
 */
class ACView constructor(context: Context, level: Int, type: String) : FormulaView(context, level) {

    private lateinit var clRoot: ConstraintLayout
    private lateinit var llUp: LinearLayout
    private lateinit var llDown: LinearLayout
    private var mType: String


    init {
        initView(type)
        mType = type

    }


    /**
     * 初始化view
     */
    private fun initView(type: String) {
        when (level) {
            1 -> {
                LayoutInflater.from(context)
                    .inflate(R.layout.layout_latex_permutation_combination_1, this)
            }
            2 -> {
                LayoutInflater.from(context)
                    .inflate(R.layout.layout_latex_permutation_combination_2, this)

            }
            else -> {
                LayoutInflater.from(context)
                    .inflate(R.layout.layout_latex_permutation_combination_3, this)
            }
        }
        val permutationCombinationText = findViewById<TextView>(R.id.permutation_combination_text)
        permutationCombinationText.text = type
        //根布局
        clRoot = findViewById(R.id.permutation_combination_root)
        //输入上
        llUp = findViewById(R.id.permutation_combination_up_root)
        //输入下
        llDown = findViewById(R.id.permutation_combination_down_root)

        setCanClickView(clRoot, false, true)
        setCanClickView(llUp, true, false)
        setCanClickView(llDown, false, false)


    }

    override fun isSpecial(clickName: String?): Boolean {
        return true
    }

    override fun toLatexString(convertResult: ConvertResult?) {
        if (mType == "A") {
            getLatexStr(convertResult, "perm")
        } else {
            getLatexStr(convertResult, "comb")
        }
    }

    private fun getLatexStr(convertResult: ConvertResult?, str: String) {
        convertResult?.let {
            it.message += "$str("
            toLatexString(llDown, it)
            it.message += ","
            toLatexString(llUp, it)
            it.message += ")"


        }
    }
}