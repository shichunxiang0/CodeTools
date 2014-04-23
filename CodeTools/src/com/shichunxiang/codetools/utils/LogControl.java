package com.shichunxiang.codetools.utils;

import android.util.Log;
/**
 * 应用调试，flag=true，输出打印信息
 * 应用上架，flag=false,进制打印信息输出，减少资源占用。
 * @author shichunxiang
 *
 */
public class LogControl {
	public static boolean flag=true;
	public static  void i(String tag,String msg){
		if(flag){
			Log.i(tag, msg);
		}
	}

}
