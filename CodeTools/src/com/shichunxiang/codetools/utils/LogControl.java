package com.shichunxiang.codetools.utils;

import android.util.Log;
/**
 * Ӧ�õ��ԣ�flag=true�������ӡ��Ϣ
 * Ӧ���ϼܣ�flag=false,���ƴ�ӡ��Ϣ�����������Դռ�á�
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
