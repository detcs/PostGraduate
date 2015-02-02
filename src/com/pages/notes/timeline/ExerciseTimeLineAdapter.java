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
 	public ExerciseTimeLineAdapter(Context context,List<String> dates) {
		super();
		//this.paths = paths;
		this.context=context;
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.dates=dates;
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
	    // When convertView is not null, we can reuse it directly, there is no need 
	    // to reinflate it. We only inflate a new View when the convertView supplied 
	    // by ListView is null. 
	    if (convertView == null) { 
	        convertView = mInflater.inflate(R.layout.item_exercise_timeline, null); 
	        // Creates a ViewHolder and store references to the two children views 
	        // we want to bind data to. 
	        holder = new ViewHolder(); 
	       
	       
	        holder.grid=(GridView)convertView.findViewById(R.id.exercise_timeline_item_grid);
	        holder.day=(TextView)convertView.findViewById(R.id.exercise_timeline_date);
	        //holder.img = (ImageView) convertView.findViewById(R.id.exercise_timeline_img); 
	        convertView.setTag(holder); 
	    } else { 
	       // Get the ViewHolder back to get fast access to the TextView 
	        // and the ImageView. 
	        holder = (ViewHolder) convertView.getTag(); 
	    }
	    String tableName=context.getResources().getString(R.string.db_english_table);
		//choosePaths(DataConstants.SD_PATH+"/"+DataConstants.PHOTO_DIR_PATH+"/"+tableName);
		SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		 List<String> photoNames=DataConstants.dbHelper.queryPhotoNamesAtDate(context, db, tableName, dates.get(position));
		List<String> photoPaths=new ArrayList<String>();
		String dirPath=FileDataHandler.APP_DIR_PATH+"/"+context.getResources().getString(R.string.dir_english);
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
	
	private List<String> getPicPathsOfDate(String date,String fileDir)
	{
		List<String> pics=null;
		Log.i("flip","dir "+fileDir);
		File dir=new File(fileDir);
		if(dir.exists())
		{
			pics=new ArrayList<String>();
			File[] tempList = dir.listFiles();
			//Log.i("flip","len "+tempList.length);
			String photoName;
			for (int i = 0; i < tempList.length; i++) 
			{
				   if (tempList[i].isFile()) 
				   {
					   photoName=tempList[i].getName();
					   Log.e("flip","pname "+photoName);
					   if(photoName.contains(date))
						   pics.add(tempList[i].getPath());
					   
				   }
			}
		}
		return pics;
	}
}
