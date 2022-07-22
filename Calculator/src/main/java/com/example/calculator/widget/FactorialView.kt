package com.example.calculator.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.calculator.R
import com.example.calculator.manager.ConvertResult

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：非 ！
 */
class FactorialView constructor(context: Context, level: Int) : FormulaView(context, level) {

    private lateinit var factorialRoot: LinearLayout
    private lateinit var factorialHorizontalRoot: LinearLayout

    init {
        innitView()
    }

    /**
     * 初始化view
     */
    private fun innitView() {
        when (level) {
            1 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_factorial_1, this)
            }
            2 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_factorial_2, this)
            }
            else -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_factorial_3, this)
            }
        }

        factorialRoot = findViewById(R.id.factorial_root)
        factorialHorizontalRoot = findViewById(R.id.factorial_horizontal_root)

        setCanClickView(factorialRoot, false, true)
        setCanClickView(factorialHorizontalRoot, true, false)

    }

    override fun isSpecial(clickName: String?): Boolean {
        return true
    }

    override fun toLatexString(convertResult: ConvertResult?) {
        convertResult?.let {
            toLatexString(factorialHorizontalRoot, it)
            it.message += " !"
            if (!it.isSuccess) {
                return@let
            }
        }
    }
}