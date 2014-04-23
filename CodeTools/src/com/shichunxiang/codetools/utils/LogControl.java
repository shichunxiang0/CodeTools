package com.shichunxiang.codetools.utils;

import android.util.Log;

public class LogControl {
	public static boolean flag=true;
	public static  void i(String tag,String msg){
		if(flag){
			Log.i(tag, msg);
		}
	}

}
