package com.example.calculator.manager;


import com.example.calculator.widget.FormulaView;
import com.example.calculator.widget.FractionView;
import com.example.calculator.widget.Sqrt2View;
import com.example.calculator.widget.SqrtView;

/**
 * view 转 latex
 */

public class View2Latex {

    public static String getLatexName(FormulaView formulaView) {
        String className = formulaView.getClass().getSimpleName();
        if (className.equals(FractionView.class.getSimpleName())) {
            return LatexConstant.Fraction;
        } else if (className.equals(SqrtView.class.getSimpleName())) {
            return LatexConstant.Sqrt;
        } else if (className.equals(Sqrt2View.class.getSimpleName())) {
            return LatexConstant.Sqrt;
        }

        return null;
    }
}
