package com.qihoo.wifiinfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.qihoo.wifiinfo.anim.HaloAnimHelper;
import com.qihoo.wifiinfo.anim.StepAnimHelper;
import com.qihoo.wifiinfo.step.StepDetector.StepDetectorListener;

public class MainActivity extends Activity implements OnClickListener {

	private Button start;
	
	private Button stop;
	
	private ImageView iv1;
	
	private ImageView iv2;
	
	private ImageView iv_left;
	
	private ImageView iv_right;
	
	private HaloAnimHelper hah;
	
	private StepAnimHelper sah;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.button1);
		stop = (Button) findViewById(R.id.button2);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);

		iv1 = (ImageView)findViewById(R.id.imageView1);
		iv2 = (ImageView)findViewById(R.id.imageView2);
		
		iv_left = (ImageView)findViewById(R.id.iv_left_foot);
		iv_right = (ImageView)findViewById(R.id.iv_right_foot);
		

		hah = new HaloAnimHelper(this, iv1, iv2);
		//hah.startAnim();
		sah = new StepAnimHelper(this, iv_left, iv_right);

	}
	
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// startActivity(new Intent(MainActivity.this,
			// RssiTestActivity.class));
			hah.startAnim();
			sah.startAnim();
			break;
			
		case R.id.button2:
			hah.stopAnim();
			sah.stopAnim();
			break;

		default:
			break;
		}
	}
}
