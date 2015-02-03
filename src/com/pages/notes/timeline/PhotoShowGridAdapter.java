package com.pages.notes.timeline;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.ydd.R;
import com.data.model.DataConstants;
import com.squareup.picasso.Picasso;

public class PhotoShowGridAdapter extends BaseAdapter
{

	List<String> imgPaths;
	Context context;
	LayoutInflater mInflater;
	public PhotoShowGridAdapter(Context context,List<String> imgPaths) {
		super();
		this.imgPaths = imgPaths;
		this.context=context;
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgPaths.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return imgPaths.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GridViewHolder holder; 
	    if (convertView == null) { 
	        convertView = mInflater.inflate(R.layout.item_timeline_grid_item, null); 
	        holder = new GridViewHolder(); 
	        holder.img = (ImageView) convertView.findViewById(R.id.exercise_timeline_img); 
	        convertView.setTag(holder); 
	    } else { 
	        holder = (GridViewHolder) convertView.getTag(); 
	    }
	    // Bind the data efficiently with the holder.
	    //Log.e(DataConstants.TAG,"pos:"+position+" path:"+ imgPaths.get(position));
	    int width=(DataConstants.screenWidth-10)/4;
	    //Log.e(DataConstants.TAG,"width:"+width);
	    Picasso.with(context).load(new File(imgPaths.get(position))).centerInside().resize(width,width).into(holder.img);
	    return convertView; 
	}
	
}
class GridViewHolder { 

    ImageView img; 
} 
