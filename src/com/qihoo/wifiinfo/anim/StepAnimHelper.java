package com.qihoo.wifiinfo.anim;

import com.qihoo.wifiinfo.R;
import com.qihoo.wifiinfo.R.anim;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class StepAnimHelper {

	private Animation left1;

	private Animation left2;

	private Animation right1;

	private Animation right2;

	private Context mContext;

	private ImageView iv_left;

	private ImageView iv_right;
	
	private boolean isStop;

	public StepAnimHelper(Context context, ImageView iv1, ImageView iv2) {
		mContext = context;
		iv_left = iv1;
		iv_right = iv2;
		iv_left.setVisibility(View.INVISIBLE);
		iv_right.setVisibility(View.INVISIBLE);
		initAnim();
	}
	
	public void startAnim(){
		iv_left.setVisibility(View.VISIBLE);
		iv_right.setVisibility(View.INVISIBLE);
		isStop = false;
		iv_left.startAnimation(left1);
		mHander.postDelayed(runnable, 500);
	}
	
	public void stopAnim(){
		//iv_left.setVisibility(View.INVISIBLE);
		//iv_right.setVisibility(View.INVISIBLE);
		isStop = true;
		mHander.removeCallbacks(runnable);
		iv_left.clearAnimation();
		iv_right.clearAnimation();
	}

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			iv_right.setVisibility(View.VISIBLE);
			iv_right.startAnimation(right1);
		}
	};
	
	private Handler mHander = new Handler();

	private void initAnim() {
		left1 = AnimationUtils.loadAnimation(mContext, R.anim.feet_step1);
		left2 = AnimationUtils.loadAnimation(mContext, R.anim.feet_step2);
		right1 = AnimationUtils.loadAnimation(mContext, R.anim.feet_step1);
		right2 = AnimationUtils.loadAnimation(mContext, R.anim.feet_step2);
		left1.setAnimationListener(alLeft1);
		left2.setAnimationListener(alLeft2);
		right1.setAnimationListener(alRight1);
		right2.setAnimationListener(alRight2);
	}

	private AnimationListener alLeft1 = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			//
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if(isStop){
				return;
			}
			iv_left.startAnimation(left2);
		}
	};

	private AnimationListener alLeft2 = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if(isStop){
				return;
			}
			iv_left.startAnimation(left1);
		}
	};

	private AnimationListener alRight1 = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if(isStop){
				return;
			}
			iv_right.startAnimation(right2);
		}
	};

	private AnimationListener alRight2 = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if(isStop){
				return;
			}
			iv_right.startAnimation(right1);
		}
	};

}
