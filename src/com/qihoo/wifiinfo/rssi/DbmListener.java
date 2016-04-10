package com.qihoo.wifiinfo.rssi;

public interface DbmListener {

	public void onResult(int dbm);
	
	public void onError();
	
	public void onDisconnect();
}
