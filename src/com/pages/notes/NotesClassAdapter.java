package com.pages.notes;

import java.util.List;

import com.app.ydd.R;
import com.data.model.DataConstants;
import com.data.model.UserConfigs;
import com.pages.viewpager.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NotesClassAdapter extends BaseAdapter{
	List<String> names;
	List<String> courseTableNames;
	Context context;
	LayoutInflater mInflater;
	public NotesClassAdapter(List<String> names, List<String> courseTableNames,Context context) {
		super();
		this.names = names;
		this.courseTableNames=courseTableNames;
		this.context = context;
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.size();
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return names.get(arg0);
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
	        convertView = mInflater.inflate(R.layout.item_course_name, null); 
	        // Creates a ViewHolder and store references to the two children views 
	        // we want to bind data to. 
	        holder = new ViewHolder(); 
	       // holder.button = (Button) convertView.findViewById(R.id.btn); 
	        holder.img=(ImageView)convertView.findViewById(R.id.course_name_img);
	        holder.courseName=(TextView)convertView.findViewById(R.id.course_name);
	        holder.courseInfo=(TextView)convertView.findViewById(R.id.course_note_info);
	        
	        convertView.setTag(holder); 
	    } else { 
	       // Get the ViewHolder back to get fast access to the TextView 
	        // and the ImageView. 
	        holder = (ViewHolder) convertView.getTag(); 
	    } 
	    holder.courseName.setText(names.get(position));
	    SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		// if(!DataConstants.dbHelper.tableIsExist(db,
		// getResources().getString(R.string.db_footprint_table)))
		int count=DataConstants.dbHelper.queryCourseRecordsCount(db,courseTableNames.get(position));
		db.close();
	    holder.courseInfo.setText(count+context.getResources().getString(R.string.piece));
	    // Bind the data efficiently with the holder. 
//	    holder.button.setText(names.get(position)); 
//	    final int pos=position;
//	    holder.button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent();
//				intent.setClass(context, ExerciseActivity.class);
//				boolean isFirstUse=UserConfigs.getIsFirstTakePhoto()==null?true:false;
//				if(isFirstUse)
//				{
//					intent.putExtra("tag", context.getResources().getString(R.string.first_use));
//				}
//				else
//				{
//					intent.putExtra("course_name",names.get(pos));
//					intent.putExtra("tag", context.getResources().getString(R.string.note_class));
//				}
//				context.startActivity(intent);
//			}
//		});
	    return convertView; 
	}
	public void refresh()
	{
		Log.e(DataConstants.TAG,"refresh");
		notifyDataSetChanged();
	}
	static class ViewHolder { 

	    Button button; 
	    ImageView img;
	    TextView courseName;
	    TextView courseInfo;
	    

	} 

}
