package com.example.calculator.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.calculator.R;
import com.hdyj.basicmodel.utils.HdyjLogKt;

/**
 * Created by hdyjzgq
 * data on 2021/3/14
 * function is ：用于显示键盘输入的内容
 *
 */

public class EditView extends FormulaView {
    private static final String TAG = "EditView";
    public EditView(Context context) {
        super(context, 1);
        initView();
    }

    public EditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public EditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        HdyjLogKt.hdyjLoge("添加编辑框");
        //重新设置latexview名称，用于操作
        latexView = getClass().getSimpleName();

        this.setBackgroundColor(getResources().getColor(R.color.transparent));
        //设置可点击控件，并且初始化选中控件
        setCanClickView(this, false, true);
    }
}
