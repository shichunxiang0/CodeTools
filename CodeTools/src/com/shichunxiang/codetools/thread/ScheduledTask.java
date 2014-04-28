package com.shichunxiang.codetools.thread;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.shichunxiang.codetools.utils.LogControl;

/**
 * 任务延时执行的几种实现方式
 * @author Administrator
 *
 */
public class ScheduledTask extends Activity{
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				
				break;
			case 2:
				LogControl.i("me", "Handler执行，方法2");
				break;
			case 3:
				
				LogControl.i("me", "Handler执行，方法3");
				Message message3=handler.obtainMessage();
				message3.what=3;
				handler.sendMessageDelayed(message3, 1000);
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view=new View(this);
		setContentView(view);
		LogControl.i("me","CurrentThreadId="+Thread.currentThread().getId()+",CurrentThreadName"+Thread.currentThread().getName());
		LogControl.i("me", "ScheduledTask");
		//方法一
//		method1();
		//方法2
//		method2();
		//方法三
//		method3();
		//方法四
//		method4();
		//方法无
		method5();
	}
	Timer timer=new Timer();
    TimerTask timerTask1=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					LogControl.i("me", "任务执行。");
				}
			});
		}
	};
	private void method1() {
		// TODO Auto-generated method stub
		timer.schedule(timerTask1, 1000,1000);
	}
	////////////////////////////////////////////
	TimerTask timerTask2=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			msg.what=2;
			handler.sendMessage(msg);
		}
	};
	private void method2() {
		// TODO Auto-generated method stub
		timer.schedule(timerTask2, 1000, 1000);
	}
	//////////////////////////////////////////
	private void method3(){
		Message msg=handler.obtainMessage();
		msg.what=3;
		handler.sendMessageDelayed(msg, 1000);
	}
    /////////////////////////////////
	Thread myThread=new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				try {
					Thread.sleep(1000);
					Message msg=handler.obtainMessage();
					msg.what=4;
					handler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	});
	private void method4(){
		myThread.start();
	}
	///////////////////////////////////////////////
	Runnable runnable=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			LogControl.i("me", "使用method5执行.");
			LogControl.i("me","CurrentThreadId="+Thread.currentThread().getId()+",CurrentThreadName="+Thread.currentThread().getName());
			boolean flag=handler.postDelayed(runnable, 1000);
		}
	};
	private void method5(){
		boolean flag=handler.postDelayed(runnable, 1000);
	}

}
