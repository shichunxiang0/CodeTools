package com.shichunxiang.codetools.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.shichunxiang.codetools.utils.LogControl;

/**
 * 
 * @author shichunxiang
 * 1.�ж��ֻ�����״̬
 * 2.��ȡ�ֻ�IP��ַ
 * 3.��ȡ�ֻ�MAC��ַ
 * 
 * Ȩ��Ҫ�� 
 *
 */
public class DeviceNetInfoUtil {
	/**
	 * ע��㲥�����ߣ�����������״̬�����ı�ʱ����Ҫ���������
	 * IntentFilter filter=new IntentFilter();
	 * filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
	 * context.registerReceiver(receiver);
	 * 
	 * ʹ����ϣ�ע��������
	 * if(receiver!=null){
	 *    context.unregisterReceiver(receiver);
	 * }
	 */
	public static BroadcastReceiver receiver=new BroadcastReceiver(){
		public void onReceive(Context context, android.content.Intent intent) {
			ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo wifiInfo=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			NetworkInfo mobileInfo=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			//ע��wifiInfo��mobileInfo����Ϊ��
			if(!mobileInfo.isConnected()&&!wifiInfo.isConnected()){
				//δ��������
			}else{
				//��������
			}
		};
	};
	/**
	 * �ж��ֻ������Ƿ�����
	 * @param context
	 * @return true ����;false δ����
	 */
	public static boolean isNetworkConnected(Context context){
	    
		if(context!=null){
			ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni=cm.getActiveNetworkInfo();
			if(ni!=null){
				LogControl.i("me", "ni.isAvailable="+ni.isAvailable());
				LogControl.i("me", "ni.isConnected="+ni.isConnected());
				LogControl.i("me", "ni.isConnectedOrConnecting="+ni.isConnectedOrConnecting());
				LogControl.i("me", "ni.isFailover="+ni.isFailover());//failover ʧЧ��Ԯ��Ϊϵͳ��Ԯ������һ�֣���ϵͳ������һ���豸ʧЧ���޷�����ʱ����һ���豸�����Զ�����ԭʧЧϵͳ��ִ�еĹ�������
				LogControl.i("me", "ni.isRoaming="+ni.isRoaming());//roam ��������������
				return ni.isAvailable();
			}
		}
		return false;
		
	}
	/**
	 * �жϵ�ǰ�������ӷ�ʽ�Ƿ�Ϊwifi
	 * @param 
	 * @return ���Ϊfalse���п�������δ����
	 */
	public static boolean isWifiConnected(Context context){
		if(context!=null){
			ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if(ni!=null){
				return ni.isConnected();
			}
		}
		return false;
	}
	/**
	 * �жϵ�ǰ�������ӷ�ʽ�Ƿ�Ϊ ͨ�� �ֻ���
	 * @param 
	 * @return ���Ϊfalse���п�������δ����
	 */
	public static boolean isMobileConnected(Context context){
		if(context!=null){
			ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if(ni!=null){
				return ni.isConnected();
			}
		}
		return false;
	}
	/**
	 * ����ֻ�������ʽ
	 * @param context
	 * @return
	 */
	public static int getConnectedType(Context context){
		if(context!=null){
			ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni=cm.getActiveNetworkInfo();
			if(ni!=null&&ni.isConnected()){
				return ni.getType();
			}
		}
		//����δ����
		return -1;
	}
	/**
	 * ��������������͵����֡�
	 * @param context
	 * @return
	 */
	public static String getConnectedTypeName(Context context){
		if(context!=null){
			ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni=cm.getActiveNetworkInfo();
			if(ni!=null){
				return ni.getTypeName();
			}
		}
		return null;
	}
	/**
	 * ����������ӵ�IPv4��ַ
	 * @return ���Ϊnull���п�������δ����
	 */
	public static String getIp(){
		try {
			Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
			if(en!=null){
				while(en.hasMoreElements()){
					Enumeration<InetAddress> addresses=en.nextElement().getInetAddresses();
					if(addresses!=null){
						while(addresses.hasMoreElements()){
							InetAddress address=addresses.nextElement();
							//loopback address �ػ���ַ����windows�лػ���ַ��127.0.0.1
							if(!address.isLoopbackAddress()&&InetAddressUtils.isIPv4Address(address.getHostAddress())){
								return address.getHostAddress();
							}
						}
					}
					
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����ֻ���MAC��ַ
	 * @param context
	 * @return
	 */
	public static String getMac(Context context){
		if(context!=null){
			WifiManager wm=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo wi=wm.getConnectionInfo();
			if(wi!=null){
				return wi.getMacAddress();
			}
		}
		return null;
	}
	
	

}
