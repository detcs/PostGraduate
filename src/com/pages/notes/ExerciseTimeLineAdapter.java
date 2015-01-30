package com.pages.notes;

import java.io.File;
import java.util.List;

import com.app.ydd.R;
import com.pages.notes.NotesClassAdapter.ViewHolder;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class ExerciseTimeLineAdapter extends BaseAdapter {

	List<String> paths;
	Context context;
	LayoutInflater mInflater;
 	public ExerciseTimeLineAdapter(List<String> paths,Context context) {
		super();
		this.paths = paths;
		this.context=context;
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return paths.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return paths.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder; 
	    // When convertView is not null, we can reuse it directly, there is no need 
	    // to reinflate it. We only inflate a new View when the convertView supplied 
	    // by ListView is null. 
	    if (convertView == null) { 
	        convertView = mInflater.inflate(R.layout.item_exercise_timeline, null); 
	        // Creates a ViewHolder and store references to the two children views 
	        // we want to bind data to. 
	        holder = new ViewHolder(); 
	        holder.img = (ImageView) convertView.findViewById(R.id.exercise_timeline_img); 
	        convertView.setTag(holder); 
	    } else { 
	       // Get the ViewHolder back to get fast access to the TextView 
	        // and the ImageView. 
	        holder = (ViewHolder) convertView.getTag(); 
	    }
	    // Bind the data efficiently with the holder.
	   // Bitmap bitmap=BitmapFactory.decodeFile(paths.get(position));
	   // .setText(); 
	    Picasso.with(context).load(new File(paths.get(position))).resize(200, 200).into(holder.img);
//	    holder.img.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent();
//				intent.setClass(context, ExerciseActivity.class);
//				intent.putExtra("tag", "");
//				context.startActivity(intent);
//			}
//		});
	    return convertView; 
	}
	static class ViewHolder { 

	    ImageView img; 

	} 
}
