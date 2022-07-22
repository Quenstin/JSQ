package com.example.calculator.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.example.calculator.R
import com.example.calculator.databinding.ActivityCalculatorBinding
import com.example.calculator.manager.*
import com.example.calculator.viewModel.CalculatorViewModel
import com.example.calculator.widget.KeyBoardLayout
import com.hdyj.basicmodel.ext.setActivityTitle
import com.hdyj.basicmodel.view.activity.BaseActivity
import com.hdyj.basicmodel.view.activity.BaseMVVMActivity
import com.hdyj.commonlibrary.ARouterPath.ToolPath

/**
 * Created by hdyjzgq
 * data on 2021/3/5
 * function is ：计算器页面
 * 取模运算还需优化
 */
@RequiresApi(Build.VERSION_CODES.N)
@Route(path = ToolPath.calculatorPath)
class CalculatorActivity : BaseMVVMActivity<CalculatorViewModel, ActivityCalculatorBinding>(),
    KeyBoardLayout.InputListener {


    override fun initView() {
        setActivityTitle("计算器")
        mViewBinding.kbl.setInputListener(this)
        initLatex()
    }

    override fun initListener() {

    }

    override fun initData() {

    }


    /**
     * 开始计算
     */
    private fun startCalculate() {
        val convertResult = ViewAssembleManager.getInstance().toLatexString()
        val message = convertResult.message
        if (message.isEmpty()) {
            return
        }
        mViewModel.setFormula(message)
        LogUtils.d("input---计算公式为： $message")
    }

    /**
     * 订阅liveData    设置最终结果
     */
    override fun initObserver() {
        mViewModel.result.observe(this, { t ->
            t?.let {
                mViewBinding.tvResult.text = it

            }
        })
    }

    /**
     * 监听键盘的输入事件
     * 后期需要提出去单独处理
     */
    override fun input(str: String?, math: Boolean) {
        if (str == getString(R.string.keyboard_log)) {
            addMathFormula(MathFormula.Log)
        } else if (str == getString(R.string.keyboard_sqrt)) {//根号
            addMathFormula(MathFormula.Sqrt)
        } else if (str == getString(R.string.keyboard_power)) {
            addMathFormula(MathFormula.Power)
        } else if (str == getString(R.string.keyboard_frac)) {
            addMathFormula(MathFormula.Fraction)
        } else if (str == getString(R.string.keyboard_ln)) {
            addMathFormula(MathFormula.Ln)
        } else if (str == getString(R.string.keyboard_sin)) {
            addMathFormula(MathFormula.Sine)
        } else if (str == getString(R.string.keyboard_cos)) {
            addMathFormula(MathFormula.Cosine)
        } else if (str == getString(R.string.keyboard_tan)) {
            addMathFormula(MathFormula.Tangent)
        } else if (str == getString(R.string.keyboard_arcsin)) {
            addMathFormula(MathFormula.ArcSine)
        } else if (str == getString(R.string.keyboard_arccos)) {
            addMathFormula(MathFormula.ArcCosine)
        } else if (str == getString(R.string.keyboard_arctan)) {
            addMathFormula(MathFormula.ArcTangent)
        } else if (str == getString(R.string.keyboard_combination)) {
            addMathFormula(MathFormula.Combination)
        } else if (str == getString(R.string.keyboard_permutation)) {
            addMathFormula(MathFormula.Permutation)
        } else if (str == getString(R.string.keyboard_lcm)) {
            addMathFormula(MathFormula.Lcm)
        } else if (str == getString(R.string.keyboard_gcd)) {
            addMathFormula(MathFormula.Gcd)
        } else if (str == getString(R.string.keyboard_abs)) {
            addMathFormula(MathFormula.AbsoluteValue)
        } else if (str == getString(R.string.keyboard_integ)) {
            addMathFormula(MathFormula.Integ)
        } else if (str == getString(R.string.keyboard_factorial)) {
            addMathFormula(MathFormula.Factorial)
        } else if (str == getString(R.string.keyboard_mod)) {//mod
            addMathFormula(MathFormula.Mod)

//            addCharacter(LatexConstant.LETTER_MOD)
        } else if (str == getString(R.string.keyboard_degree)) {//度
            addCharacter(LatexConstant.DEGREE)
        } else if (str == getString(R.string.keyboard_minute)) { // '
            addCharacter(LatexConstant.MINUTE)
        } else if (str == getString(R.string.keyboard_second)) {//"
            addCharacter(LatexConstant.SECOND)
        } else if (str == getString(R.string.keyboard_pi)) {//π
            addCharacter(LatexConstant.PI)
        } else if (str == getString(R.string.keyboard_e)) {//e
            addCharacter(LatexConstant.LETTER_E)
        } else if (str == getString(R.string.keyboard_y)) {//y
            addCharacter(LatexConstant.LETTER_Y)
        } else if (str == getString(R.string.keyboard_plus)) {//+
            addCharacter(LatexConstant.PLUS)
        } else if (str == getString(R.string.keyboard_minus)) { //-
            addCharacter(LatexConstant.MINUS)
        } else if (str == getString(R.string.keyboard_multiply)) {//*
            addCharacter(LatexConstant.MULTIPLICATION)
        } else if (str == getString(R.string.keyboard_divide)) {//➗
            addCharacter(LatexConstant.DIVISION)
        } else if (str == getString(R.string.keyboard_equal)) {//=
            startCalculate()
//            addCharacter(LatexConstant.EQUAL)
        } else if (str == getString(R.string.keyboard_left_paren)) {//(
            addCharacter(LatexConstant.Parenthesis_Left)
        } else if (str == getString(R.string.keyboard_right_paren)) {//)
            addCharacter(LatexConstant.Parenthesis_Right)
        } else if (str == getString(R.string.keyboard_percent)) {//%
            addCharacter(LatexConstant.PERCENT)
        } else if (str == getString(R.string.keyboard_x)) {// x
            addCharacter(LatexConstant.LETTER_X)
        } else if (str == getString(R.string.keyboard_zero)) {
            addCharacter(LatexConstant.Num_0)
        } else if (str == getString(R.string.keyboard_one)) {
            addCharacter(LatexConstant.Num_1)
        } else if (str == getString(R.string.keyboard_two)) {
            addCharacter(LatexConstant.Num_2)
        } else if (str == getString(R.string.keyboard_three)) {
            addCharacter(LatexConstant.Num_3)
        } else if (str == getString(R.string.keyboard_four)) {
            addCharacter(LatexConstant.Num_4)
        } else if (str == getString(R.string.keyboard_five)) {
            addCharacter(LatexConstant.Num_5)
        } else if (str == getString(R.string.keyboard_six)) {
            addCharacter(LatexConstant.Num_6)
        } else if (str == getString(R.string.keyboard_seven)) {
            addCharacter(LatexConstant.Num_7)
        } else if (str == getString(R.string.keyboard_eight)) {
            addCharacter(LatexConstant.Num_8)
        } else if (str == getString(R.string.keyboard_nine)) {
            addCharacter(LatexConstant.Num_9)
        } else if (str == getString(R.string.keyboard_delete)) {
            Assembles.instants?.deleteMathFormula(this)

        } else if (str == getString(R.string.keyboard_clear)) {
            clearContent()

        } else if (str == getString(R.string.keyboard_cursor_left)) {
            Assembles.instants?.leftOrRightIndex(getString(R.string.keyboard_left))

        } else if (str == getString(R.string.keyboard_cursor_right)) {
            Assembles.instants?.leftOrRightIndex(getString(R.string.keyboard_right))

        } else if (str == getString(R.string.keyboard_dot)) {
            addCharacter(LatexConstant.DECIMAL_POINT)
        }

    }

    /**
     * 清空屏幕和输入
     */
    private fun clearContent() {
        Assembles.instants?.removeAll()
        mViewBinding.evLatexView.removeAllViews()
        initLatex()
        mViewBinding.tvResult.text = ""
    }

    /**
     * 添加简单的符号view
     */
    private fun addCharacter(str: String) {
        Assembles.instants?.addSimpleSymbol(this, str)

    }

    /**
    * 添加自定义公式View
    */
    private fun addMathFormula(mathFormula: MathFormula) {
        Assembles.instants?.addMathFormula(this, mathFormula)

    }


    /**
     * 监听键盘是否翻页
     */
    override fun pageChanged(i: Int) {
        LogUtils.d("input----$i")
    }

    /**
     * 初始化函数库
     */
    private fun initLatex() {
        LateXConfig.getInstance().setEditRoot(mViewBinding.evLatexView, mViewBinding.hsvLatex)
    }





    override fun onDestroy() {
        super.onDestroy()
        LateXConfig.getInstance().hsvLatex = null
        ViewAssembleManager.getInstance().release()
    }


}