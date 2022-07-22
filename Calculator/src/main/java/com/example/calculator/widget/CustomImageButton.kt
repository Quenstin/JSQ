package com.example.calculator.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.example.calculator.R

/**
 * Created by hdyjzgq
 * data on 2021/3/5
 * function is ：键盘按键view
 */
class CustomImageButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    i: Int = 0
) : AppCompatImageButton(context, attributeSet, i) {


     var cl: ColorStateList?

    init {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.CustomImageButton, i, 0)
        cl = obtainStyledAttributes.getColorStateList(0)
        obtainStyledAttributes.recycle()
    }


    override fun drawableStateChanged() {
        super.drawableStateChanged()
        val colorStateList = cl
        if (colorStateList != null && colorStateList.isStateful) {
            setColorFilter(cl!!.getColorForState(drawableState, 0))
        }
    }

    fun setColorFilter(csl: ColorStateList) {
        cl = csl;
        setColorFilter(csl.getColorForState(drawableState, 0))

    }


}