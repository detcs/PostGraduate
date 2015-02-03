package com.view.util;

import com.app.ydd.R;
import com.data.model.Post;
import com.data.util.ComputeURL;
import com.data.util.DataBuffer;
import com.data.util.PostUtil;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @author : wsy
 * @time : 2015/1/30
 * 
 * @last edit:wsy
 * @last time:1/30
 * 
 * */
public class SquareAdapter extends BaseAdapter implements AdapterFresh {
	// private static final String TAG="SquareAdapter";
	private Context context;
	private DataBuffer<Post> buffer;

	public SquareAdapter(Context context) {
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
	public Post getItem(int position) {
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
		Post vg = getItem(position);
		if (null == vg) {
			return LayoutInflater.from(context).inflate(
					R.layout.list_item_wait, parent, false);
		}
		ViewHolder holder;
		if (null == convertView || null == convertView.getTag()) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.square_list_item, parent, false);
			holder = new ViewHolder();
			holder.head = (ImageView) convertView.findViewById(R.id.headView);
			holder.nickName = (TextView) convertView
					.findViewById(R.id.nickName);
			holder.time = (TextView) convertView.findViewById(R.id.timeView);
			holder.comments = (TextView) convertView
					.findViewById(R.id.countView);
			holder.content = (TextView) convertView
					.findViewById(R.id.contentView);
			holder.title = (TextView) convertView.findViewById(R.id.titleView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.vg = vg;
		holder.nickName.setText(vg.getAuthor());
		holder.time.setText(vg.getTime());
		holder.comments.setText(vg.getComments() + "條評論");
		holder.title.setText(vg.getTitle());
		holder.content.setText("  " + vg.getContent());
		Picasso.with(context).load(ComputeURL.getHeadImgURL(vg.getImgNo()))
				.resize(100, 100).placeholder(R.drawable.default_head)
				.error(R.drawable.default_head).into(holder.head);
		return convertView;
	}

	public static class ViewHolder {
		public Post vg;// for information trans
		TextView nickName;
		TextView time;
		TextView title;
		TextView content;
		TextView comments;
		ImageView head;
	}

	// ****************init****************
	private void init() {
		buffer = new DataBuffer<Post>(this, new PostUtil());
	}

	@Override
	public void fresh() {
		// TODO Auto-generated method stub
		buffer.clean();
	}

}
