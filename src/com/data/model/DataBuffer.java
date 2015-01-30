package com.data.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.util.LruCache;
import android.widget.BaseAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.data.util.Json_util;
import com.pages.funsquare.essence.EssenseFragment;
import com.view.util.ViewGenerator;

public class DataBuffer {
	private RequestQueue requestQueue;
	private BaseAdapter adapter;
	private int type;
	private static final int ITEMPERSCREEN = 15;// 控制屏幕的“长度”
	private int coefficient;
	private LruCache<Integer, Object> cache;

	public DataBuffer(int dataType, BaseAdapter adapter) {
		requestQueue = GloableData.requestQueue;
		type = dataType;
		this.adapter = adapter;
		cache = new LruCache<Integer, Object>(2 * ITEMPERSCREEN);
		coefficient = 1;
	}

	public int getCount() {
		return coefficient * ITEMPERSCREEN;
	}

	public ViewGenerator getDataItem(int index) {
		Object aim = cache.get(index);
		if (null == aim) {
			// send request:
			sendRequest(index);
			return null;
		}
		return (ViewGenerator) aim;
	}

	// *********************util*********************
	private String getURL(int page) {
		String reStr = GloableData.URL + EssenseFragment.PATH
				+ GloableData.getParam() + "&page=" + page + "&limit="
				+ ITEMPERSCREEN + "&type=" + type;
		return reStr;
	}

	private void sendRequest(final int index) {
		final int page = index / ITEMPERSCREEN + 1;
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, getURL(page), null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						int errorCode = -2;
						try {
							errorCode = (Integer) response.get("errorCode");
							if (errorCode != 0) {
								return;
							}
							List<ViewGenerator> list = Json_util
									.transToMaterial(response, type,
											ITEMPERSCREEN);
							int base = (page - 1) * ITEMPERSCREEN;
							for (int i = 0; i < list.size(); i++) {
								cache.put(i + base, list.get(i));
							}
							if (index / ITEMPERSCREEN == coefficient - 1) {
								coefficient++;
							}
							adapter.notifyDataSetChanged();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Log.i("error", "wsy");
					}
				});
		requestQueue.add(jsonObjectRequest);
	}
}
