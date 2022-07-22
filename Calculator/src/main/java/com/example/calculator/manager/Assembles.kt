package com.example.calculator.manager

import android.content.Context
import com.example.calculator.widget.FormulaView
import com.example.calculator.widget.SimpleSymbolView

/**
 * Created by hdyjzgq
 * data on 2021/3/9
 * function is ：latex组装器
 */
class Assembles {

    /**
     * 添加自定义公式
     */
    fun addMathFormula(context: Context?, mathFormula: MathFormula?): FormulaView? {
        if (ViewAssembleManager.getInstance().selectedViewLevel > 3) {
            return null
        }
        val selectedStruct = ViewAssembleManager.getInstance().selectedStruct
        val viewGroup = MathFormulaFactory().newInstance(
            context, mathFormula,
            ViewAssembleManager.getInstance().selectedViewLevel
        )
        ViewAssembleManager.getInstance().addMathFormula(viewGroup, selectedStruct)
        return viewGroup
    }

    /**
     * 添加文字
     */
    fun addSimpleSymbol(
        context: Context?,
        content: String?
    ): SimpleSymbolView {
        val symbolView = MathFormulaFactory().newInstance(
            context, content,
            ViewAssembleManager.getInstance().selectedViewLevelForSymbol
        )
        ViewAssembleManager.getInstance().addSimpleSymbol(symbolView)
        return symbolView
    }

    /**
     * 按顺序单个删除
     */
    fun deleteMathFormula(context: Context?) {
        ViewAssembleManager.getInstance().deleteMathFormula()
    }

    /**
     * 删除所有
     */
    fun removeAll(){
        ViewAssembleManager.getInstance().resetData()
    }

    /**
     * 移动光标
     */
    fun leftOrRightIndex(flag:String){
        ViewAssembleManager.getInstance().setLinViewIndex(flag)
    }

    companion object {
        @JvmStatic
        var instants: Assembles? = null
            get() {
                synchronized(Assembles::class.java) {
                    if (field == null) {
                        field = Assembles()
                    }
                }
                return field
            }
            private set
    }
}