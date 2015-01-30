package com.data.model;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.telephony.TelephonyManager;

public class GloableData {
	public static RequestQueue requestQueue;
	public static final String URL = "http://114.215.196.179:8080/gs";
	public static String param;
	private static String userid = "6dfae24f-a77a-11e4-9812-ac853dac2305";
	private static String verify = "1";

	public static void init(Context context) {

		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// param = "&deviceid=" + tm.getDeviceId() + "&userid=" + userid
		// + "&verify=" + verify;
		 param = "&deviceid=" + 1 + "&userid=" + userid
					 + "&verify=" + verify;

		requestQueue = Volley.newRequestQueue(context);
	}

	public static String getParam() {
		return param;
	}
}
