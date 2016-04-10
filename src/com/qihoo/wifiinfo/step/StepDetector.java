package com.qihoo.wifiinfo.step;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepDetector {

	private Context mContext;

	private SensorManager mSensorManager;

	private StepSensorListener mStepSensorListener;

	private Sensor mSensor;
	
	private StepDetectorListener sdListener;

	public StepDetector(Context context , StepDetectorListener listener) {
		sdListener = listener;
		mContext = context;
		init();
	}

	private void init() {
		mStepSensorListener = new StepSensorListener(sdListener);
		mSensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	public void start() {
		mSensorManager.registerListener(mStepSensorListener, mSensor,
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	public void stop() {
		if (mSensorManager != null && mStepSensorListener != null) {
			mSensorManager.unregisterListener(mStepSensorListener);
		}

	}
	
	public interface StepDetectorListener{
		public void onStep();
	}

	private class StepSensorListener implements SensorEventListener {

		private float mScale;
		private float mYOffset;
		private float mLastValues[] = new float[3 * 2];
		private float mLastDirections[] = new float[3 * 2];
		private float mLastExtremes[][] = { new float[3 * 2], new float[3 * 2] };
		private float mLastDiff[] = new float[3 * 2];
		private int mLastMatch = -1;
		float mLimit = (float) 10.00;// 1.97  2.96  4.44  6.66  10.00  15.00  22.50  33.75  50.62
		private StepDetectorListener mListener;

		public StepSensorListener(StepDetectorListener listener) {
			mListener = listener;
			int h = 480;
			mYOffset = h * 0.5f;
			mScale = -(h * 0.5f * (1.0f / (SensorManager.MAGNETIC_FIELD_EARTH_MAX)));
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			Sensor sensor = event.sensor;
			synchronized (this) {
				if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
					float vSum = 0;
					for (int i = 0; i < 3; i++) {
						vSum += (mYOffset + event.values[i] * mScale);
					}
					int k = 0;
					float v = vSum / 3;
					float temp = v < mLastValues[0] ? -1 : 0;
					float direction = v > mLastValues[0] ? 1 : temp;
					if (direction == - mLastDirections[0]){
						int extType = (direction > 0 ? 0 : 1);
						mLastExtremes[extType][k] = mLastValues[k];
						float diff = Math.abs(mLastExtremes[extType][k] - mLastExtremes[1 - extType][k]);
						if (diff > mLimit){
							boolean isAlmostAsLargeAsPrevious = diff > (mLastDiff[k]*2/3);
                            boolean isPreviousLargeEnough = mLastDiff[k] > (diff/3);
                            boolean isNotContra = (mLastMatch != 1 - extType);
                            if (isAlmostAsLargeAsPrevious && isPreviousLargeEnough && isNotContra) {
                            	mListener.onStep();
                            	mLastMatch = extType;
                            }
                            else{
                            	mLastMatch = -1;
                            }
						}
						mLastDiff[k] = diff;
					}
					mLastDirections[k] = direction;
                    mLastValues[k] = v;
				}
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	}

}
