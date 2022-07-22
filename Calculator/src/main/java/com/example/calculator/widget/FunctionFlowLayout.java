package com.example.calculator.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdyjzgq
 * data on 2021/3/14
 * function is ：暂时不用
 *
 */
public class FunctionFlowLayout extends ViewGroup implements OnClickListener {
    public int a;
    public List<b> b;
    public int width;
    public int height;
    public boolean e;
    public FunctionListener mFunctionListener;
    public String g;

    public interface FunctionListener {
        void a(int i, String str);
    }

    public class b {
        public List<View> a = new ArrayList();
        public int b;
        public int c;
        public int d;
        public int e;

        public b(int i) {
            this.e = (i - FunctionFlowLayout.this.getPaddingLeft()) - FunctionFlowLayout.this.getPaddingRight();
        }

        public boolean a(View view) {
            int measuredWidth = view.getMeasuredWidth();
            if (this.a.size() == 0) {
                this.c = this.b + measuredWidth;
            } else {
                this.c = (FunctionFlowLayout.this.width + this.b) + measuredWidth;
            }
            if ((this.c > this.e ? 1 : null) != null) {
                return false;
            }
            this.a.add(view);
            this.b = this.c;
            int measuredHeight = view.getMeasuredHeight();
            measuredWidth = this.d;
            if (measuredWidth >= measuredHeight) {
                measuredHeight = measuredWidth;
            }
            this.d = measuredHeight;
            return true;
        }

        public void a(boolean z, int i) {
            int size = this.a.size();
            if (size != 0) {
                int paddingLeft = FunctionFlowLayout.this.getPaddingLeft();
                int i2 = (this.e - this.b) / size;
                for (int i3 = 0; i3 < size; i3++) {
                    View view = (View) this.a.get(i3);
                    int measuredWidth = view.getMeasuredWidth();
                    int measuredHeight = view.getMeasuredHeight();
                    if (z) {
                        measuredWidth += i2;
                        view.getLayoutParams().width = measuredWidth;
                        if (i2 > 0) {
                            view.measure(MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY));
                        }
                    }
                    int round = Math.round(((float) (this.d - measuredHeight)) / 2.0f) + i;
                    view.layout(paddingLeft, round, paddingLeft + measuredWidth, measuredHeight + round);
                    paddingLeft += FunctionFlowLayout.this.width + measuredWidth;
                }
            }
        }
    }

    public FunctionFlowLayout(Context context) {
        this(context, null);
    }

    public FunctionFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunctionFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList();
        this.e = true;
        this.width = dp2px(context, 10.0f);
        this.height = dp2px(context, 10.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FunctionFlowLayout);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == 0) {
                this.width = obtainStyledAttributes.getDimensionPixelOffset(index, this.width);
            } else if (index == 2) {
                this.height = obtainStyledAttributes.getDimensionPixelOffset(index, this.height);
            } else if (index == 1) {
                this.e = obtainStyledAttributes.getBoolean(index, this.e);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public final void clear() {
        this.b.clear();
        int i = this.a;
        if (i > 0) {
            b bVar = new b(i);
            i = getChildCount();
            b bVar2 = bVar;
            for (int i2 = 0; i2 < i; i2++) {
                View childAt = getChildAt(i2);
                childAt.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                if (!bVar2.a(childAt)) {
                    this.b.add(bVar2);
                    bVar2 = new b(this.a);
                    bVar2.a(childAt);
                }
            }
            if (!this.b.contains(bVar2)) {
                this.b.add(bVar2);
            }
        }
    }

    public void addString(int i, String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            i = getChildCount();
            this.a = 0;
            this.g = str2;
            if (i != 0) {
                removeAllViews();
                return;
            }
            return;
        }
        removeAllViews();
        this.a = i;
        this.g = str2;
        LayoutInflater.from(getContext()).inflate(R.layout.result_text_layout, this);
        TextView textView = (TextView) getChildAt(0);
        textView.setText(str);
        textView.setOnClickListener(this);
        clear();
        invalidate();
    }

    public void addString(int i, List<String> list, String str) {
        if (list != null) {
            removeAllViews();
            this.a = i;
            this.g = str;
            for (String str2 : list) {
                LayoutInflater.from(getContext()).inflate(R.layout.result_label_layout, this);
                TextView textView = (TextView) getChildAt(getChildCount() - 1);
                textView.setText(str2);
                textView.setOnClickListener(this);
            }
            clear();
            invalidate();
            return;
        }
        i = getChildCount();
        this.a = 0;
        this.g = "";
        if (i != 0) {
            removeAllViews();
        }
    }

    public void onClick(View view) {
        if (this.mFunctionListener != null && (view instanceof TextView)) {
            String valueOf = String.valueOf(((TextView) view).getText());
            if (!TextUtils.isEmpty(valueOf)) {
                if (valueOf.equals(getContext().getString(R.string.edit_draw_image))) {
                    this.mFunctionListener.a(1, this.g);
                    return;
                }
                FunctionListener aVar = this.mFunctionListener;
                int i;
                if (valueOf.equals(getContext().getString(R.string.edit_derivation))) {
                    i = 2;
                } else if (valueOf.equals(getContext().getString(R.string.edit_integral))) {
                    i = 3;
                } else if (valueOf.equals(getContext().getString(R.string.edit_polynomial_decomposition))) {
                    i = 5;
                } else if (valueOf.equals(getContext().getString(R.string.edit_polynomial_combination))) {
                    i = 4;
                } else {
                    aVar.a(6, valueOf.substring(1).replaceAll(",", ""));
                    return;
                }
                aVar.a(i, this.g);
            }
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int size = this.b.size();
        i3 = getPaddingTop();
        i = 0;
        while (i < size) {
            b bVar = (b) this.b.get(i);
            if (!this.e || i == size - 1) {
                bVar.a(false, i3);
            } else {
                bVar.a(true, i3);
            }
            i3 += bVar.d + this.height;
            i++;
        }
    }

    public void onMeasure(int i, int i2) {
        if (this.a <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        i = this.b.size();
        int i3 = 0;
        for (i2 = 0; i2 < i; i2++) {
            int i4 = ((b) this.b.get(i2)).d + i3;
            if (i2 != i - 1) {
                i4 += this.height;
            }
            i3 = i4;
        }
        setMeasuredDimension(this.a, getPaddingBottom() + (getPaddingTop() + i3));
    }

    public void setFlowClickListener(FunctionListener aVar) {
        this.mFunctionListener = aVar;
    }
}