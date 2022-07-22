package com.example.calculator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


import androidx.annotation.Nullable;

import com.example.calculator.R;
import com.example.calculator.manager.ConvertResult;
import com.example.calculator.manager.LatexConstant;
import com.example.calculator.manager.View2Latex;

import java.util.logging.Logger;

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：分数视图
 *
 */

public class FractionView extends FormulaView {
    private static final String TAG = "FractionView";
    private LinearLayout ll_Root, ll_NumeratorRoot, ll_DenominatorRoot;

    public FractionView(Context context, int level) {
        super(context, level);
        initView();
    }

    protected FractionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    protected FractionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        Log.d(TAG, "--->添加分数公式, 级别：" + level);
        if (level == 1) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_fraction_1, this);
        } else if (level == 2) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_fraction_2, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_fraction_3, this);
        }

        ll_Root = (LinearLayout) findViewById(R.id.fraction_root);
        ll_NumeratorRoot = (LinearLayout) findViewById(R.id.fraction_up_root);
        ll_DenominatorRoot = (LinearLayout) findViewById(R.id.fraction_down_root);
        //设置可点击控件，并且初始化选中控件
        setCanClickView(ll_Root, false, true);
        setCanClickView(ll_NumeratorRoot, true, false);
        setCanClickView(ll_DenominatorRoot, false, false);
    }

    @Override
    public void toLatexString(ConvertResult convertResult) {
        convertResult.message += "(";
        //添加分子
        toLatexString(ll_NumeratorRoot, convertResult);
        if (!convertResult.isSuccess) {
            return;
        }
        convertResult.message += "/";
        //添加分母
        toLatexString(ll_DenominatorRoot, convertResult);
        convertResult.message += ")";

    }

    @Override
    public void parseLatexString(String string) {

    }
}
