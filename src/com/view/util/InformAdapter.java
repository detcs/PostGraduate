package com.view.util;

import com.app.ydd.R;
import com.data.model.Inform;
import com.data.util.DataBuffer;
import com.data.util.InformUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InformAdapter extends BaseAdapter implements AdapterFresh {
	private Context context;
	private DataBuffer<Inform> buffer;

	public InformAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return buffer.getCount();
	}

	@Override
	public Inform getItem(int position) {
		// TODO Auto-generated method stub
		return buffer.getDataItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Inform vg = getItem(position);
		if (null == vg) {
			return LayoutInflater.from(context).inflate(
					R.layout.list_item_wait, parent, false);
		}
		ViewHolder holder;
		if (null == convertView || null == convertView.getTag()) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.square_inform_item, parent, false);
			holder = new ViewHolder();
			holder.titleView = (TextView) convertView
					.findViewById(R.id.titleView);
			holder.commentView = (TextView) convertView
					.findViewById(R.id.commentView);
			holder.tagView = (TextView) convertView.findViewById(R.id.tagView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.pid = vg.getId();
		holder.titleView.setText(vg.getTitle());
		holder.commentView.setText(vg.getContent());
		holder.tagView.setText(vg.getIsNewTag());
		return convertView;
	}

	public static class ViewHolder {
		TextView titleView;
		TextView commentView;
		TextView tagView;
		public String pid;
	}

	// ********************init********************
	private void init() {
		buffer = new DataBuffer<Inform>(this, new InformUtil());
	}

	@Override
	public void fresh() {
		// TODO Auto-generated method stub
		buffer.clean();
	}
}
