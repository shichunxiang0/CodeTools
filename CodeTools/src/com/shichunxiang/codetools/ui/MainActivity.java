package com.shichunxiang.codetools.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.codetools.R;
import com.shichunxiang.codetools.net.DeviceNetInfoUtil;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		DeviceNetInfoUtil.isNetworkConnected(this);
	}

}
