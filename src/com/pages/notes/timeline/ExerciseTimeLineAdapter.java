package com.pages.notes.timeline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.app.ydd.R;
import com.data.model.DataConstants;
import com.data.model.FileDataHandler;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseTimeLineAdapter extends BaseAdapter {

	//List<String> paths;
	Context context;
	LayoutInflater mInflater;
	List<String> dates;
	String tableName;
 	public ExerciseTimeLineAdapter(Context context,String tableName,List<String> dates) {
		super();
		//this.paths = paths;
		this.context=context;
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.dates=dates;
		this.tableName=tableName;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dates.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dates.get(arg0);
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
	    if (convertView == null) { 
	        convertView = mInflater.inflate(R.layout.item_exercise_timeline, null); 
	        
	        holder = new ViewHolder(); 
	        holder.grid=(GridView)convertView.findViewById(R.id.exercise_timeline_item_grid);
	        holder.day=(TextView)convertView.findViewById(R.id.exercise_timeline_date);
	        //holder.img = (ImageView) convertView.findViewById(R.id.exercise_timeline_img); 
	        convertView.setTag(holder); 
	    } else { 
	       
	        holder = (ViewHolder) convertView.getTag(); 
	    }
	    //String tableName=context.getResources().getString(R.string.db_english_table);
		//choosePaths(DataConstants.SD_PATH+"/"+DataConstants.PHOTO_DIR_PATH+"/"+tableName);
		SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		 List<String> photoNames=DataConstants.dbHelper.queryPhotoNamesAtDate(context, db, tableName, dates.get(position));
		List<String> photoPaths=new ArrayList<String>();
		String dirPath=FileDataHandler.APP_DIR_PATH+"/"+DataConstants.TABLE_DIR_MAP.get(tableName);
		 for(String name:photoNames)
			photoPaths.add(dirPath+"/"+name);
		db.close();
		
	    // Bind the data efficiently with the holder.
	    holder.grid.setAdapter(new PhotoShowGridAdapter(context,photoPaths));
	    holder.day.setText(dates.get(position));
	    return convertView; 
	}
	static class ViewHolder { 

		TextView day;
	    GridView grid;

	} 
	

}
