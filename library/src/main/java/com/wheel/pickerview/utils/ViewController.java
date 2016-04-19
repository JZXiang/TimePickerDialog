package com.wheel.pickerview.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wheel.pickerview.R;

/**
 * 控制popwindow半透明黑背景渐隐渐现
 * Created by jzxiang on 16/3/14.
 */
public class ViewController implements Animation.AnimationListener {

    View mViewShader;
    ViewGroup mContentView;
    View mRootLayout;
    Animation mAnimBgIn, mAnimBgOut, mAnimViewIn, mAnimViewOut;


    public ViewController(Activity activity, View rootLayout) {
        mRootLayout = rootLayout;
        init(activity);
    }

    public ViewController(Activity activity, int rootLayoutId) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        mRootLayout = inflater.inflate(rootLayoutId, mContentView, false);
        init(activity);
    }

    void init(Activity activity) {
        initView(activity);
        initAnim(activity);
    }

    void initView(Activity activity) {
        mContentView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        mViewShader = new View(activity);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mViewShader.setLayoutParams(params);
    }

    void initAnim(Activity activity) {
        mAnimBgIn = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in);
        mAnimBgOut = AnimationUtils.loadAnimation(activity, android.R.anim.fade_out);
        mAnimBgOut.setFillAfter(true);
        mAnimBgOut.setAnimationListener(this);

        mAnimViewIn = AnimationUtils.loadAnimation(activity, R.anim.slide_in_bottom);
        mAnimViewOut = AnimationUtils.loadAnimation(activity, R.anim.slide_out_bottom);
    }


    public void show() {
        mContentView.addView(mViewShader);
        mContentView.addView(mRootLayout);
        mViewShader.startAnimation(mAnimBgIn);
    }


    public void dismiss() {
        mViewShader.startAnimation(mAnimBgOut);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mContentView.post(new Runnable() {
            @Override
            public void run() {
                mContentView.removeView(mViewShader);
            }
        });
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
