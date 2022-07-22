package com.example.calculator.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.calculator.R;
import com.example.calculator.manager.ConvertResult;
import com.hdyj.basicmodel.utils.HdyjLogKt;


/**
 * 绝对值
 */
public class AbsoluteValueView extends FormulaView {
    private static final String TAG = "AbsoluteValueView";
    private LinearLayout llyVmatrix;

    public AbsoluteValueView(Context context, int level) {
        super(context, level);
        initView();
    }

    public AbsoluteValueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AbsoluteValueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        HdyjLogKt.hdyjLoge("--->添加矩阵组公式, 级别：" + level);

        if (level == 1) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_absolute_value_1, this);
        } else if (level == 2) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_absolute_value_2, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_absolute_value_3, this);
        }

        ConstraintLayout llyVmatrixRoot = (ConstraintLayout) findViewById(R.id.absolute_value_root);          // 根布局
        llyVmatrix = (LinearLayout) findViewById(R.id.absolute_value_horizontal_root);          // 底数布局

        //设置可点击控件，并且初始化选中控件
        setCanClickView(llyVmatrixRoot, false, true);
        setCanClickView(llyVmatrix, true, false);
    }

    @Override
    public boolean isSpecial(String clickName) {
        return true;
    }


    public void toLatexString(ConvertResult convertResult) {
        //添加数据
        convertResult.message +="abs(";
        toLatexString(llyVmatrix, convertResult);
        if (!convertResult.isSuccess) {
            return;
        }
        convertResult.message +=")";
    }

    @Override
    public void parseLatexString(String string) {

    }
}
