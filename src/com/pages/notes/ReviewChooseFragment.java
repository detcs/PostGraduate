package com.pages.notes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.app.ydd.R;
import com.data.model.DataConstants;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

public class ReviewChooseFragment extends Fragment{

	ListView exerciseTimeLine;
	Button reviewReverse;
	Button reviewEbbin;
	//List<String> paths;
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_reviewchoose, container, false);
		exerciseTimeLine=(ListView)rootView.findViewById(R.id.exercise_timeline_list);
		//paths=new ArrayList<String>();
		String tableName=getResources().getString(R.string.db_english_table);
		//choosePaths(DataConstants.SD_PATH+"/"+DataConstants.PHOTO_DIR_PATH+"/"+tableName);
		SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		TreeSet<String> dateSet=DataConstants.dbHelper.queryDates(getActivity(), db, tableName);
		List<String> dates=new ArrayList<String>();
		for(String date:dateSet)
			dates.add(date);
		db.close();
		exerciseTimeLine.setAdapter(new ExerciseTimeLineAdapter(getActivity(),dates));
		reviewReverse=(Button)rootView.findViewById(R.id.reverse_review);
		reviewReverse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				jumpToReview();
			}
		});
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	public void jumpToReview()
	{
		Fragment fragment=new ReviewFragment();
		Bundle bundle = new Bundle();  
        bundle.putString("type", "");  
        fragment.setArguments(bundle);
		FragmentManager fm=getActivity().getSupportFragmentManager();
		FragmentTransaction trans = fm.beginTransaction();  
		trans.replace(R.id.exercise_frame, fragment);
		trans.commit();
	}
	private void choosePaths(String fileDir)
	{
		Log.i("flip","dir "+fileDir);
		File dir=new File(fileDir);
		if(dir.exists())
		{
			Log.i("flip","dir exist");
		}
		File[] tempList = dir.listFiles();
		Log.i("flip","len "+tempList.length);
		for (int i = 0; i < tempList.length; i++) 
		{
			   if (tempList[i].isFile()) 
			   {
				 //  paths.add(tempList[i].getPath());
			   Log.i("flip","path"+tempList[i].getPath());
			   }
		}
	}
	public String getSDPath(){
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState()
		.equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
		if (sdCardExist)
		{
			Log.i("flip","SD exist");
			sdDir = Environment.getExternalStorageDirectory();//获取跟目录
		}
		return sdDir.toString();
		}
}
