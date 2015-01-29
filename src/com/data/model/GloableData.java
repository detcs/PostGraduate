package com.data.model;

import android.content.Context;
import android.telephony.TelephonyManager;

public class GloableData {
	public static final String URL = "114.215.196.179:8080/gs";
	public static String param;
	private static String userid = "6dfae24f-a77a-11e4-9812-ac853dac2305";
	private static String verify = "00a88168-c92f-4cf5-898b6e9a4527e32f";

	public static void init(Context context) {

		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		param = "&deviceid=" + tm.getDeviceId() + "&userid=" + userid
				+ "&verify=" + verify;
	}

	public static String getParam() {
		return param;
	}
}
