package com.view.util;

import com.app.ydd.R;
import com.data.model.DataBuffer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/*
 * author:wsy
 * time:2015/1/29
 * last change:2015/1/29
 * 
 * */

public class MyAdapter extends BaseAdapter {
	private Context context;
	private DataBuffer buffer;
	private int type;

	public MyAdapter(Context context, int dataType) {
		// TODO Auto-generated constructor stub
		this.context = context;
		type = dataType;
		init();
	}

	@Override
	public int getCount() {
		return buffer.getCount();
	}

	@Override
	public ViewGenerator getItem(int position) {
		return buffer.getDataItem(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.essense_list_item, parent, false);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.titleView);
			holder.author = (TextView) convertView
					.findViewById(R.id.authorView);
			holder.time = (TextView) convertView.findViewById(R.id.timeView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ViewGenerator vg = getItem(position);
		String author = ViewGenerator.DEFAULT_AUTHOR;
		String title = ViewGenerator.DEFAULT_TITLE;
		String time = ViewGenerator.DEFAULT_TIME;
		if (null != vg) {
			author = vg.getAuthor();
			title = vg.getTitle();
			time = vg.getTime();
		}
		holder.author.setText(author);
		holder.title.setText(title);
		holder.time.setText(time);
		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView author;
		TextView time;
	}

	// *********************init*********************
	private void init() {
		initVariable();
	}

	private void initVariable() {
		// init buffer
		buffer = new DataBuffer(type, this);
	}
}
