package com.example.calculator.manager;

import android.content.Context;

import com.example.calculator.widget.ACView;
import com.example.calculator.widget.AbsoluteValueView;
import com.example.calculator.widget.AngleView;
import com.example.calculator.widget.ArcSCTView;
import com.example.calculator.widget.ArcView;
import com.example.calculator.widget.Equation3View;
import com.example.calculator.widget.EquationView;
import com.example.calculator.widget.FactorialView;
import com.example.calculator.widget.FormulaView;
import com.example.calculator.widget.FractionView;
import com.example.calculator.widget.IntegView;
import com.example.calculator.widget.LcmGcdView;
import com.example.calculator.widget.LineView;
import com.example.calculator.widget.LnView;
import com.example.calculator.widget.LogView;
import com.example.calculator.widget.ModView;
import com.example.calculator.widget.Power10View;
import com.example.calculator.widget.PowerView;
import com.example.calculator.widget.SCTView;
import com.example.calculator.widget.SimpleSymbolView;
import com.example.calculator.widget.Sqrt2View;
import com.example.calculator.widget.SqrtView;
import com.example.calculator.widget.SubScriptView;
import com.example.calculator.widget.SumView;
import com.example.calculator.widget.SuperiorView;


/**
 * Created by hdyjzgq
 * data on 2021/3/9
 * function is ：创建数学公式功能
 * 该类是个工厂类 用于根据接收到的事件来添加不同的自定义view
 */

public class MathFormulaFactory {

    public MathFormulaFactory() {

    }

    public FormulaView newInstance(Context context, MathFormula formula, int level) {
        if (formula.equals(MathFormula.Fraction)) {
            return new FractionView(context, level);
        }
        //根号
        else if (formula.equals(MathFormula.Sqrt)) {
            return new SqrtView(context, level);
        } else if (formula.equals(MathFormula.Sqrt2)) {
            return new Sqrt2View(context, level);
        }
        //幂次方
        else if (formula.equals(MathFormula.Power)) {
            return new PowerView(context, level, null, 0);
        } else if (formula.equals(MathFormula.Power2)) {
            return new PowerView(context, level, null, 2);
        } else if (formula.equals(MathFormula.PowerX2)) {
            return new PowerView(context, level, "x", 2);
        } else if (formula.equals(MathFormula.Power10)) {
            return new Power10View(context, level);
        }
        //角度
        else if (formula.equals(MathFormula.Sine)) {
            return new SCTView(context, level, "sin");
        } else if (formula.equals(MathFormula.Cosine)) {
            return new SCTView(context, level, "cos");
        } else if (formula.equals(MathFormula.Tangent)) {
            return new SCTView(context, level, "tan");
        }
        //反正弦 余弦 tan
        else if (formula.equals(MathFormula.ArcSine)) {
            return new ArcSCTView(context, level, "arcsin");
        } else if (formula.equals(MathFormula.ArcCosine)) {
            return new ArcSCTView(context, level, "arccos");
        } else if (formula.equals(MathFormula.ArcTangent)) {
            return new ArcSCTView(context, level, "arctan");
        }
        //πr²
        else if (formula.equals(MathFormula.PIR2)) {
            return new PowerView(context, level, "πr", 2);
        }
        //方程式
        else if (formula.equals(MathFormula.Equation)) {
            return new EquationView(context, level);
        } else if (formula.equals(MathFormula.Equation_3)) {
            return new Equation3View(context, level);
        }
        //绝对值
        else if (formula.equals(MathFormula.AbsoluteValue)) {
            return new AbsoluteValueView(context, level);
        }
        //下标
        else if (formula.equals(MathFormula.SubScript)) {
            return new SubScriptView(context, level, null, null);
        }
        else if (formula.equals(MathFormula.SubScriptX1)) {
            return new SubScriptView(context, level, "x", "1");
        }
        else if (formula.equals(MathFormula.SubScriptX2)) {
            return new SubScriptView(context, level, "x", "2");
        }
        //三角
        else if (formula.equals(MathFormula.Angle)) {
            return new AngleView(context, level);
        }
        //度数，分，秒
        else if (formula.equals(MathFormula.Circ)) {
            return new SuperiorView(context, level, "°");
        }
        else if (formula.equals(MathFormula.Minute)) {
            return new SuperiorView(context, level, "'");
        }
        else if (formula.equals(MathFormula.Second)) {
            return new SuperiorView(context, level, "''");
        }
        //求和
        else if (formula.equals(MathFormula.Sum)) {
            return new SumView(context, level);
        }
        //弧
        else if (formula.equals(MathFormula.Arc)) {
            return new ArcView(context, level);
        }
        //log
        else if (formula.equals(MathFormula.Log)){
            return new LogView(context,level);
        }
        //ln
        else if (formula.equals(MathFormula.Ln)){
            return new LnView(context, level);
        }
        //A吕
        else if (formula.equals(MathFormula.Combination)){
            return new ACView(context,level,"C");
        }
        //C吕
        else if (formula.equals(MathFormula.Permutation)){
            return new ACView(context,level,"A");
        }
        //公倍
        else if (formula.equals(MathFormula.Lcm)){
            return new LcmGcdView(context,level,"lcm");
        }
        //公约
        else if (formula.equals(MathFormula.Gcd)){
            return new LcmGcdView(context,level,"gcd");

        }
        //非
        else if (formula.equals(MathFormula.Factorial)){
            return new FactorialView(context,level);

        }
        //integ
        else if (formula.equals(MathFormula.Integ)){
            return new IntegView(context,level);

        }
        //取模
        else if (formula.equals(MathFormula.Mod)){
            return new ModView(context,level);

        }
        return null;
    }

    public SimpleSymbolView newInstance(Context context, String content, int level) {
        if (context == null || content == null) {
            return null;
        }
        return new SimpleSymbolView(context, content, level);
    }
}
