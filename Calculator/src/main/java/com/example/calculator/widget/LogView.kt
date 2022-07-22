package com.example.calculator.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.LogUtils
import com.example.calculator.R
import com.example.calculator.manager.ConvertResult

/**
 * Created by hdyjzgq
 * data on 2021/3/9
 * function is ：log函数
 */
class LogView constructor(
    context: Context,
    level: Int
) : FormulaView(context,level) {

    private lateinit var log_root:ConstraintLayout
    private lateinit var log_left_root:LinearLayout
    private lateinit var log_right_root:LinearLayout



    init {
        initView()

    }

    private fun initView() {
        LogUtils.d("当前级别--------$level")
        when (level) {
            1 -> {
            LayoutInflater.from(context).inflate(R.layout.layout_latex_log_1,this)
            }
            2 -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_log_2,this)
            }
            else -> {
                LayoutInflater.from(context).inflate(R.layout.layout_latex_log_3,this)
            }
        }
        log_root=findViewById(R.id.log_root)
        log_left_root=findViewById(R.id.log_left_root)
        log_right_root=findViewById(R.id.log_right_root)

        setCanClickView(log_root,false,true)
        setCanClickView(log_left_root,true,false)
        setCanClickView(log_right_root,false,false)
    }

    override fun isSpecial(clickName: String?): Boolean {
        if (clickName.equals(resources.getResourceEntryName(log_right_root.id))){
            return true
        }
        return false
    }

    override fun toLatexString(convertResult: ConvertResult?) {
        convertResult?.let {
            it.message+="log("
            toLatexString(log_left_root,it)
            it.message+=","
            toLatexString(log_right_root,it)
            it.message+=")"
            if (!it.isSuccess){
                return@let
            }
        }
    }
}