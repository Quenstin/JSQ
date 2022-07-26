package com.example.calculator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.calculator.R;
import com.example.calculator.manager.ConvertResult;
import com.example.calculator.manager.LatexConstant;
import com.example.calculator.manager.MathFormulaFactory;


/**
 * Created by hdyjzgq
 * data on 2021/3/10
 * function is ：'  "  度 视图
 *
 */

public class SuperiorView extends FormulaView {
    private static final String TAG = "SuperiorView";
    private TextView tvSuperior;
    private LinearLayout superior1_root, llyParamRoot;

    public SuperiorView(Context context, int level, String superior) {
        super(context, level);
        initView(superior);
    }

    protected SuperiorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(null);
    }

    protected SuperiorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(null);
    }


    private void initView(String superior) {
        Log.d(TAG, "--->添加******公式, 级别：" + level);
        if (level == 1) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_superior_1, this);
        } else if (level == 2) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_superior_2, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_latex_superior_3, this);
        }

        superior1_root = (LinearLayout) findViewById(R.id.superior_root);
        tvSuperior = (TextView) findViewById(R.id.tvSuperior);
        tvSuperior.setText(superior);
        llyParamRoot = (LinearLayout) findViewById(R.id.llyParamRoot);
        //设置可点击控件，并且初始化选中控件
        setCanClickView(superior1_root, false, true);
        setCanClickView(llyParamRoot, true, false);

        if (superior != null && !superior.isEmpty()) {
            SimpleSymbolView symbolView = new MathFormulaFactory().newInstance(getContext(),
                    String.valueOf(superior), level + 1);

            addChildView(getResources().getResourceEntryName(R.id.llyPower), -1, symbolView);
        }
    }

    @Override
    public boolean isSpecial(String clickName) {
        return true;
    }

    @Override
    public void toLatexString(ConvertResult convertResult) {
        String superior = tvSuperior.getText().toString();
        convertResult.message += superior;


        if (superior.equals("°")) {
            convertResult.message += "^";
            convertResult.message += LatexConstant.Brace_Left;
            convertResult.message += "\\circ";
            convertResult.message += LatexConstant.Brace_Right;
            toLatexString(llyParamRoot, convertResult);

        } else {

            toLatexString(llyParamRoot, convertResult);

        }
    }

    @Override
    public void parseLatexString(String string) {

    }
}
