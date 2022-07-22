package com.example.calculator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.calculator.R;
import com.example.calculator.utils.ExtensionKt;

/**
 * Created by hdyjzgq
 * data on 2021/3/14
 * function is ：自定义键盘
 *
 */
public class KeyBoardLayout extends LinearLayout implements View.OnClickListener {
    private ViewPager mViewPager;
    private InputListener mInputListener;

    public KeyBoardLayout(Context context) {
        this(context, null);
    }

    public KeyBoardLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyBoardLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_keyboard, this, true);
    }

    public void setInputListener(InputListener inputListener) {
        this.mInputListener = inputListener;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        mViewPager = findViewById(R.id.view_pager_keyboard);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.cursor_left).setOnClickListener(this);
        findViewById(R.id.cursor_right).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
        mViewPager.setAdapter(new KeyBoardPagerAdapter());
        mViewPager.addOnPageChangeListener(new ViewPagerPageChangeListener());
    }

    @Override
    public void onClick(View v) {
        if (mInputListener == null) {
            return;
        }
        int resourceId = -1;
        int id = v.getId();
        if (id == R.id.clear) {
            resourceId = R.string.keyboard_clear;
        } else if (id == R.id.cursor_left) {
            resourceId = R.string.keyboard_cursor_left;
        } else if (id == R.id.cursor_right) {
            resourceId = R.string.keyboard_cursor_right;
        } else if (id == R.id.delete) {
            resourceId = R.string.keyboard_delete;
        } else if (id == R.id.equal) {
            resourceId = R.string.keyboard_equal;
        } else {
            return;
        }
        mInputListener.input(getContext().getString(resourceId), false);
    }

    private class ViewPagerPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (KeyBoardLayout.this.mInputListener != null) {
                KeyBoardLayout.this.mInputListener.pageChanged(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class KeyBoardPagerAdapter extends PagerAdapter implements OnClickListener {

        @Override
        public void onClick(View view) {
            InputListener cVar = KeyBoardLayout.this.mInputListener;
            if (cVar != null) {
                cVar.input(view.getTag().toString(), true);
            }
        }


        private int[] layouts = new int[]{
                R.layout.math_keyboard_page1,
                R.layout.math_keyboard_page2,
                R.layout.math_keyboard_page4
        };

        @Override
        public int getCount() {
            return layouts.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ViewGroup parent;
            ViewGroup root;
            View inflate = LayoutInflater.from(KeyBoardLayout.this.getContext()).inflate(layouts[position], container, false);
            for (View view : ExtensionKt.getTypeChild(inflate, CustomImageButton.class)) {
                view.setOnClickListener(KeyBoardPagerAdapter.this);
            }
            container.addView(inflate);
            return inflate;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    public interface InputListener {
        void pageChanged(int i);

        void input(String str, boolean math);

    }
}
