package com.example.calculator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.calculator.R;

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：自定义光标
 */

public class LineView extends FormulaView {
    private static final String TAG = "LineView";
    public LineView(Context context, int level) {
        super(context, 1);
        initView(level);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(int level) {
        Log.d(TAG, "添加编辑竖线");
        //重新设置latexview名称，用于操作
        latexView = getClass().getSimpleName();

        if (level == 1) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_line_1, this);
        } else if (level == 2){
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_line_2, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_line_3, this);
        }
    }

    @Override
    public boolean isRootSelected() {
        return true;
    }
}
