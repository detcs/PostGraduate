package com.view.util;

import com.android.volley.RequestQueue;


import com.data.model.DataBuffer;
import com.data.model.GloableData;
import com.pages.funsquare.essence.EssenseFragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private DataBuffer<?> buffer;
	private RequestQueue requestQueue;
	private int type;

	public MyAdapter(Context context, int dataType, RequestQueue requestQueue) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.requestQueue = requestQueue;
		this.type = type;
		init();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// return buffer.getCount();
		return buffer.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// ViewGenerator generator = buffer.getDataItem(position);
		// return generator.getBriefView();
		TextView textView = new TextView(context);
		textView.setText("textView: " + position);
		return textView;
	}

	// *********************init*********************
	private void init() {

	}

	// *********************util*********************
	private String getURL(int page, int limit, int type) {
		String reStr = GloableData.URL + EssenseFragment.PATH
				+ GloableData.getParam() + "&page=" + page + "&limit=" + limit
				+ "&type=" + type;
		return reStr;
	}
}
