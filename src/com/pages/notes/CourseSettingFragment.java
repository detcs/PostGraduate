package com.pages.notes;

import java.io.File;
import java.util.List;

import com.app.ydd.R;
import com.data.model.DataConstants;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CourseSettingFragment extends Fragment {
	Button english1;
	Button english2;
	Button english3;
	ImageView imgEng1;
	ImageView imgEng2;
	ImageView imgEng3;

	Button math1;
	Button math2;
	Button math3;
	ImageView imgMath1;
	ImageView imgMath2;
	ImageView imgMath3;
	Button politics;
	ImageView imgPolitic;
	
	EditText professEdit;
	Button complete;
//	int[] englishButtonIds={R.id.english1,R.id.english2,R.id.english3};
//	int[] englishChooseImgIds={R.id.english1_choose,R.id.english2_choose,R.id.english3_choose};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_course_set, container, false);
		
		english1=(Button)rootView.findViewById(R.id.english1);
		english2=(Button)rootView.findViewById(R.id.english2);
		english3=(Button)rootView.findViewById(R.id.english3);
		imgEng1=(ImageView)rootView.findViewById(R.id.english1_choose);
		imgEng2=(ImageView)rootView.findViewById(R.id.english2_choose);
		imgEng3=(ImageView)rootView.findViewById(R.id.english3_choose);
		english1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.e(DataConstants.TAG,"click "+" "+R.id.english1);
				imgEng1.setVisibility(View.VISIBLE);
				imgEng2.setVisibility(View.INVISIBLE);
				imgEng3.setVisibility(View.INVISIBLE);
			}
		});
		english2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imgEng1.setVisibility(View.INVISIBLE);
				imgEng2.setVisibility(View.VISIBLE);
				imgEng3.setVisibility(View.INVISIBLE);
			}
		});
		english3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imgEng1.setVisibility(View.INVISIBLE);
				imgEng2.setVisibility(View.INVISIBLE);
				imgEng3.setVisibility(View.VISIBLE);
			}
		});
		
		math1=(Button)rootView.findViewById(R.id.math1);
		math2=(Button)rootView.findViewById(R.id.math2);
		math3=(Button)rootView.findViewById(R.id.math3);
		imgMath1=(ImageView)rootView.findViewById(R.id.math1_choose);
		imgMath2=(ImageView)rootView.findViewById(R.id.math2_choose);
		imgMath3=(ImageView)rootView.findViewById(R.id.math3_choose);
		math1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.e(DataConstants.TAG,"click "+" "+R.id.math1);
				imgMath1.setVisibility(View.VISIBLE);
				imgMath2.setVisibility(View.INVISIBLE);
				imgMath3.setVisibility(View.INVISIBLE);
			}
		});
		math2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imgMath1.setVisibility(View.INVISIBLE);
				imgMath2.setVisibility(View.VISIBLE);
				imgMath3.setVisibility(View.INVISIBLE);
			}
		});
		math3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imgMath1.setVisibility(View.INVISIBLE);
				imgMath2.setVisibility(View.INVISIBLE);
				imgMath3.setVisibility(View.VISIBLE);
			}
		});
		
		politics=(Button)rootView.findViewById(R.id.politics);
		imgPolitic=(ImageView)rootView.findViewById(R.id.politics_choose);
		politics.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.e(DataConstants.TAG,"click "+" "+R.id.english1);
				if(imgPolitic.getVisibility()==View.INVISIBLE)
					imgPolitic.setVisibility(View.VISIBLE);
				else
					imgPolitic.setVisibility(View.INVISIBLE);
				
			}
		});
		
		professEdit=(EditText)rootView.findViewById(R.id.input_profess_course);
		complete=(Button)rootView.findViewById(R.id.complete_choose);
		complete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				makeCourseFileDir();
			}
		});
		return rootView;
	}
	private void makeCourseFileDir()
	{
		if   (DataConstants.SD_PATH!=null)      //如果SD卡存在，则获取跟目录
		{                               
			DataConstants.SD_PATH = Environment.getExternalStorageDirectory().toString();//获取跟目录 
			String dirPath=DataConstants.SD_PATH+"/"+DataConstants.PHOTO_DIR_PATH;
			File dir=new File(dirPath+"/"+getResources().getString(R.string.english_dir));
			if(!dir.exists())
				dir.mkdir();
			dir=new File(dirPath+"/"+getResources().getString(R.string.politics_dir));
			if(!dir.exists())
				dir.mkdir();
		}   
	}
}
