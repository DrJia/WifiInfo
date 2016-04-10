package com.qihoo.wifiinfo.rssi;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;

public class DBM {

	private Context mContext;

	private WifiManager wifi_service;

	private WifiInfo wifiinfo;

	private DbmListener mListener;

	private Timer timer = new Timer(true);

	public DBM(Context context) {

		mContext = context;

		wifi_service = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

	}

	private int getRssi() {
		wifiinfo = wifi_service.getConnectionInfo();
		return wifiinfo.getRssi();
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mListener.onResult(msg.arg1);
				break;

			default:
				break;
			}
		}

	};

	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			int rssi = getRssi();
			Message msg = new Message();
			msg.what = 1;
			msg.arg1 = rssi;
			mHandler.sendMessage(msg);
		}
	};

	public void startGetDBM(DbmListener listener) {
		mListener = listener;
		timer.schedule(task, 0, 200);
	}

	public void stopGetDBM() {
		timer.cancel();
	}
}
