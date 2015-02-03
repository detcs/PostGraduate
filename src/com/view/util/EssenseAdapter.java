package com.view.util;

import com.app.ydd.R;
import com.data.model.Essense;
import com.data.util.DataBuffer;
import com.data.util.EssenseUtil;

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

public class EssenseAdapter extends BaseAdapter implements AdapterFresh {
	private Context context;
	private DataBuffer<Essense> buffer;
	private int type;
	private String queryKey;

	public EssenseAdapter(Context context, int dataType, String queryKey) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.queryKey = queryKey;
		type = dataType;
		init();
	}

	@Override
	public int getCount() {
		return buffer.getCount();
	}

	@Override
	public Essense getItem(int position) {
		return buffer.getDataItem(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Essense vg = getItem(position);
		if (null == vg) {
			return LayoutInflater.from(context).inflate(
					R.layout.list_item_wait, parent, false);
		}
		ViewHolder holder;
		if (null == convertView || null == convertView.getTag()) {
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
		holder.author.setText(vg.getAuthor());
		holder.title.setText(vg.getTitle());
		holder.time.setText(vg.getTime());
		holder.id = vg.getId();
		return convertView;
	}

	public static class ViewHolder {
		TextView title;
		TextView author;
		TextView time;
		public String id;
	}

	// *********************init*********************
	private void init() {
		initVariable();
	}

	private void initVariable() {
		// init buffer
		buffer = new DataBuffer<Essense>(this, new EssenseUtil(type, queryKey));
	}

	public void search(String queryKey) {
		buffer.clean(new EssenseUtil(type, queryKey));
	}

	@Override
	public void fresh() {
		// TODO Auto-generated method stub
		buffer.clean();
	}
}
