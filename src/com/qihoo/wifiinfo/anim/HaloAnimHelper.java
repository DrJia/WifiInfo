package com.qihoo.wifiinfo.anim;

import android.app.Activity;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HaloAnimHelper {
	
	private Activity mActivity;
	
	private int width;
	
	private ImageView iv1;
	
	private ImageView iv2;
	
	private AnimationSet circleHaloAnim1;
	
	private AnimationSet circleHaloAnim2;

	public HaloAnimHelper(Activity activity , ImageView iv1 , ImageView iv2) {
		mActivity = activity;
		width = getWidthParam();
		this.iv1 = iv1;
		this.iv2 = iv2;
		initImageView();
	}
	
	public void startAnim(){
		iv1.setVisibility(View.VISIBLE);
		iv1.startAnimation(circleHaloAnim1);
		mHander.postDelayed(runnable, 1000);
	}
	
	public void stopAnim(){
		iv1.setVisibility(View.INVISIBLE);
		iv2.setVisibility(View.INVISIBLE);
		mHander.removeCallbacks(runnable);
		iv1.clearAnimation();
		iv2.clearAnimation();
	}
	
	private Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			iv2.setVisibility(View.VISIBLE);
			iv2.startAnimation(circleHaloAnim2);
		}
	};
	
	private Handler mHander = new Handler();
	
	private AnimationSet getAnim(){
		AnimationSet circleHaloAnim = new AnimationSet(true);
		AlphaAnimation alphaAnim1 = new AlphaAnimation(1, 0);
		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 2.5f, 1.0f, 2.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		
		alphaAnim1.setFillAfter(true);
		alphaAnim1.setDuration(2000);
		alphaAnim1.setRepeatCount(AlphaAnimation.INFINITE);
		alphaAnim1.setRepeatMode(AlphaAnimation.RESTART);
		
		scaleAnim.setFillAfter(true);
		scaleAnim.setDuration(2000);
		scaleAnim.setRepeatCount(ScaleAnimation.INFINITE);
		scaleAnim.setRepeatMode(ScaleAnimation.RESTART);
		
		circleHaloAnim.addAnimation(scaleAnim);
		circleHaloAnim.addAnimation(alphaAnim1);
		
		//
		//new AccelerateInterpolator()
		circleHaloAnim.setInterpolator(new DecelerateInterpolator());
		
		return circleHaloAnim;
	}
	
	private void initImageView(){
		iv1.setVisibility(View.INVISIBLE);
		iv2.setVisibility(View.INVISIBLE);
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(width, width);
		layout.addRule(RelativeLayout.CENTER_IN_PARENT);
		iv1.setLayoutParams(layout);
		iv2.setLayoutParams(layout);
		
		circleHaloAnim1 = getAnim();
		circleHaloAnim2 = getAnim();
		
	}

	private int getWidthParam(){
		DisplayMetrics displayMetrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return (int) (displayMetrics.widthPixels * 0.5f);
	}
}
