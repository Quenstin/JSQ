package com.example.calculator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.calculator.R;
import com.example.calculator.manager.ConvertResult;


/**
 * Created by hdyjzgq
 * data on 2021/3/9
 * function is ：反正弦，反余弦 反Tan
 */
public class ArcSCTView extends FormulaView {
    private TextView tvSCT;
    private LinearLayout ll_Sine_Root, ll_Sine_None_Root;
    private String mSct;

    public ArcSCTView(Context context, int level, String sct) {
        super(context, level);
        initView(sct);
        mSct = sct;
    }

    public ArcSCTView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(null);
    }

    public ArcSCTView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(null);
    }

    private void initView(String sct) {
        if (level == 1) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_sct_1, this);
        } else if (level == 2) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_sct_2, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_sct_3, this);
        }

        ll_Sine_Root = (LinearLayout) findViewById(R.id.sct_root);          // 根布局
        tvSCT = (TextView) findViewById(R.id.sct_text);
        tvSCT.setText(sct);
        tvSCT.setTextColor(getResources().getColor(R.color.white));
        ll_Sine_None_Root = (LinearLayout) findViewById(R.id.sct_horizontal_root);

        //设置可点击控件，并且初始化选中控件
        setCanClickView(ll_Sine_Root, false, true);
        setCanClickView(ll_Sine_None_Root, true, false);
    }

    @Override
    public boolean isSpecial(String clickName) {
        return true;
    }

    @Override
    public void toLatexString(ConvertResult convertResult) {
        if (("arcsin").equals(mSct)) {
            getLatexStr(convertResult,"asin");

        } else if ("arccos".equals(mSct)) {
            getLatexStr(convertResult,"acos");

        } else {
            getLatexStr(convertResult,"atan");

        }
    }

    private void getLatexStr(ConvertResult convertResult,String type) {
        convertResult.message +=type+"(";
        //添加数据
        toLatexString(ll_Sine_None_Root, convertResult);
        convertResult.message +=")";

        if (!convertResult.isSuccess) {
            return;
        }
    }
}
