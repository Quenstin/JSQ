package com.example.calculator.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.calculator.R;
import com.example.calculator.manager.ConvertResult;
import com.hdyj.basicmodel.utils.HdyjLogKt;


/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：<角度试图  暂未用到
 *
 */

public class AngleView extends FormulaView {
    private static final String TAG = "AngleView";
    private LinearLayout llyParamRoot;

    public AngleView(Context context, int level) {
        super(context, level);
        initView();
    }

    protected AngleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    protected AngleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        HdyjLogKt.hdyjLoge("--->添加三角公式, 级别：" + level);
        if (level == 1) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_angle_1, this);
        } else if (level == 2) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_angle_2, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_angle_3, this);
        }

        LinearLayout angle_root = (LinearLayout) findViewById(R.id.angle_root);
        llyParamRoot = (LinearLayout) findViewById(R.id.llyParamRoot);
        //设置可点击控件，并且初始化选中控件
        setCanClickView(angle_root, false, true);
        setCanClickView(llyParamRoot, true, false);
    }

    @Override
    public void toLatexString(ConvertResult convertResult) {
        convertResult.message += "\\angle ";

        toLatexString(llyParamRoot, convertResult);
    }

    @Override
    public void parseLatexString(String string) {

    }
}
