package com.example.calculator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.calculator.R;
import com.example.calculator.manager.ConvertResult;
import com.example.calculator.manager.LatexConstant;
import com.example.calculator.manager.MathFormulaFactory;

/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：平方 立方 幂次方公式
 *
 */

public class PowerView extends FormulaView {
    private static final String TAG = "PowerView";
    private ConstraintLayout llyPowerRoot;
    private LinearLayout llyMultiplier, llyPower;

    public PowerView(Context context, int level, String nultiplier, int power) {
        super(context, level);
        initView(nultiplier, power);
    }

    public PowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(null, 0);
    }

    public PowerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(null, 0);
    }

    private void initView(String multiplier, int power) {
        Log.d(TAG, "--->添加幂次方公式, 级别：" + level);

        if (level == 1) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_power_1, this);
        } else if (level == 2) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_power_2, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_power_3, this);
        }

        llyPowerRoot = (ConstraintLayout) findViewById(R.id.power_root);          // 根布局
        llyMultiplier = (LinearLayout) findViewById(R.id.power_left_root);          // 底数布局
        llyPower = (LinearLayout) findViewById(R.id.power_right_root);//平方数

        //设置可点击控件，并且初始化选中控件
        setCanClickView(llyPowerRoot, false, true);
        setCanClickView(llyMultiplier, true, false);
        setCanClickView(llyPower, false, false);

        if (multiplier != null && !multiplier.isEmpty()) {
            for (int i = 0; i < multiplier.length(); ++i) {
                SimpleSymbolView symbolView = new MathFormulaFactory().newInstance(getContext(),
                        String.valueOf(multiplier.charAt(i)), level);

                addChildView(getResources().getResourceEntryName(R.id.llyMultiplier), -1, symbolView);
            }
        }

        if (power != 0) {
            SimpleSymbolView symbolView = new MathFormulaFactory().newInstance(getContext(),
                    String.valueOf(power), level + 1);

            addChildView(getResources().getResourceEntryName(R.id.llyPower), -1, symbolView);
        }
    }

    @Override
    public boolean isSpecial(String clickName) {
        if (clickName.equals(getResources().getResourceEntryName(llyMultiplier.getId()))) {
            return true;
        }
        return false;
    }

    @Override
    public void toLatexString(ConvertResult convertResult) {
        //添加乘数
        convertResult.message+="(";
        toLatexString(llyMultiplier, convertResult);
        convertResult.message+=")";

        if (!convertResult.isSuccess) {
            return;
        }
        convertResult.message += "^";
        //添加指数
        convertResult.message+="(";
        toLatexString(llyPower, convertResult);
        convertResult.message+=")";



    }

    @Override
    public void parseLatexString(String string) {
        Log.d(TAG, "power latex：" + string);

        String[] strings = string.split("^");
        if (strings.length == 2) {
            parseLatexString(llyMultiplier, strings[0]);
            parseLatexString(llyMultiplier, strings[1].substring(1, strings[1].length() - 1));
        } else {
            Log.e(TAG, "计算出错：" + string);
        }
    }
}
