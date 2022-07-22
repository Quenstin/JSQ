package com.example.calculator.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.hdyj.basicmodel.utils.HdyjLogKt;


public class CustomTextView extends AppCompatTextView {
    private static final String TAG = CustomTextView.class.getSimpleName();
    private Typeface iconfont;

    public CustomTextView(Context context) {
        super(context);
        this.init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    private void init(Context context) {
        try {
            this.iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        } catch (Exception var3) {
            HdyjLogKt.hdyjLoge(" 加载 iconfont 字体文件失败");
        }

        this.setTypeface(this.iconfont);
    }
}
