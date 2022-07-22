package com.example.calculator.manager

/**
 * latex转view
 */
object Latex2View {
    @JvmStatic
    fun getFormulaType(latex: String): MathFormula? {
        if (latex == LatexConstant.Fraction) {
            return MathFormula.Fraction
        } else if (latex == LatexConstant.Sqrt) {
            return MathFormula.Sqrt
        }
        return null
    }
}