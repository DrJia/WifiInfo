package com.qihoo.wifiinfo;

import java.util.Timer;
import java.util.TimerTask;

import com.qihoo.wifiinfo.rssi.DBM;
import com.qihoo.wifiinfo.rssi.DbmListener;
import com.qihoo.wifiinfo.step.StepDetector;
import com.qihoo.wifiinfo.step.StepDetector.StepDetectorListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class RssiTestActivity extends Activity {
	
	private DBM dbm;
	
	private StepDetector sd;
	
	private TextView tv_left;
	
	private TextView tv_dbm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssi_test);
		
		tv_left = (TextView)findViewById(R.id.left_seconds);
		tv_dbm = (TextView)findViewById(R.id.current_value);
		
		dbm = new DBM(getApplicationContext());
		sd = new StepDetector(getApplicationContext(), sdListener);
		sd.start();
	}
	
	private int dbm2Asu(int dbm){
		return (dbm+113)/2;
	}

	private StepDetectorListener sdListener = new StepDetectorListener() {

		@Override
		public void onStep() {
			startTest();
			sd.stop();
		}
	};
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				tv_left.setText("" + num);
				int ave = totalAsu/asuNum;
				
				tv_dbm.setText("average:" + ave);
				
				dbm.stopGetDBM();
				break;
				
			case 2:
				tv_left.setText("" + num);
				break;

			default:
				break;
			}
		}

	};
	
	private Timer timer = new Timer(true);
	
	private int num = 10;

	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			//int rssi = getRssi();
			num--;
			
			if(num == 0){
				timer.cancel();
				Message msg = new Message();
				msg.what = 1;
				//msg.arg1 = rssi;
				mHandler.sendMessage(msg);
				
			}else{
				Message msg = new Message();
				msg.what = 2;
				//msg.arg1 = rssi;
				mHandler.sendMessage(msg);
			}
			
		}
	};
	
	private void startTest(){
		num = 10;
		totalAsu = 0;
		asuNum = 0;
		timer.schedule(task, 1000, 1000);
		dbm.startGetDBM(listener);
	}
	
	

	@Override
	protected void onDestroy() {
		if(sd != null){
			sd.stop();
		}
		if(dbm != null){
			dbm.stopGetDBM();
		}
		super.onDestroy();
	}
	
	private int totalAsu = 0;
	
	private int asuNum = 0;

	private DbmListener listener = new DbmListener() {

		@Override
		public void onResult(int dbm) {
			int asu = dbm2Asu(dbm);
			asuNum++;
			totalAsu += asu;
			tv_dbm.setText("current:" + asu);
			Log.d("jiabin", "dbm:" + dbm);
		}

		@Override
		public void onError() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDisconnect() {
			// TODO Auto-generated method stub

		}
	};

}
