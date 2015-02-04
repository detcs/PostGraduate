package com.pages.notes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.app.ydd.R;
import com.data.model.DataConstants;
import com.data.model.FileDataHandler;
import com.data.util.SysCall;
import com.pages.viewpager.MainActivity;
import com.squareup.picasso.Picasso;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReviewFragment extends Fragment{

	Button leftBtn;
	Button rightBtn;
	ImageView reviewImg;
	List<String> reviewImgPaths;
	List<String> photoNames;
	SQLiteDatabase db;
	String tableName;
	int index=0;
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_review, container, false);
		leftBtn=(Button)rootView.findViewById(R.id.master);
		rightBtn=(Button)rootView.findViewById(R.id.unmaster);
		reviewImg=(ImageView)rootView.findViewById(R.id.review_img);
		Bundle bundle=getArguments();
		String type=bundle.getString("type");
		if(type.equals(getResources().getString(R.string.today_rec)))
		{
			leftBtn.setText(getResources().getString(R.string.get));
			rightBtn.setText(getResources().getString(R.string.collect));
		}
		else
		{
			String date=bundle.getString("date");
			tableName=getArguments().getString("course_table_name");
			db = DataConstants.dbHelper.getReadableDatabase();
			photoNames=DataConstants.dbHelper.queryPhotoNamesAtDate(getActivity(), db, tableName, date);
			List<String> photoPaths=new ArrayList<String>();
			String dirPath=FileDataHandler.APP_DIR_PATH+"/"+DataConstants.TABLE_DIR_MAP.get(tableName);
			reviewImgPaths=new ArrayList<String>();
			for(String name:photoNames)
				reviewImgPaths.add(dirPath+"/"+name);
			db.close();
			Picasso.with(getActivity()).load(new File(reviewImgPaths.get(0))).into(reviewImg);
			leftBtn.setOnClickListener(new CourseMasterListener(photoNames.get(index), tableName, getResources().getString(R.string.state_master)));
			rightBtn.setOnClickListener(new CourseMasterListener(photoNames.get(index), tableName, getResources().getString(R.string.state_unmaster)));
			final TextView remarkContext=(TextView)rootView.findViewById(R.id.remark_content);
			remarkContext.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa一一一一一一一一一一一一一一一一一一一一一一一一一一一一一");
			remarkContext.setOnClickListener(new OnClickListener() {
				Boolean flag = true;
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(flag){
				        flag = false;
				        remarkContext.setEllipsize(null); // 展开
				        remarkContext.setSingleLine(flag);
				        }else{
				        	Log.e(DataConstants.TAG,"remarkcontent "+flag);
				          flag = true;
				          remarkContext.setEllipsize(android.text.TextUtils.TruncateAt.END); // 收缩
				          remarkContext.setLines(2);
				    }
				}
			});
			final LinearLayout editRemarkLayout=(LinearLayout)rootView.findViewById(R.id.remark_edit_hideBar);
			final EditText editRemark = (EditText) rootView.findViewById(R.id.remark_edit);
			TextView cancelEdit=(TextView)rootView.findViewById(R.id.remark_edit_quitView);
			TextView saveEdit=(TextView)rootView.findViewById(R.id.remark_edit_saveView);
			ImageView editRemarkImg=(ImageView)rootView.findViewById(R.id.edit_remark_img);
			editRemarkImg.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					editRemarkLayout.setVisibility(View.VISIBLE);
					SysCall.bumpSoftInput(editRemark, getActivity());
				}
			});
			cancelEdit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(editRemarkLayout.getVisibility()==View.VISIBLE)
					{
						editRemarkLayout.setVisibility(View.INVISIBLE);
						SysCall.hideSoftInput(editRemarkLayout, getActivity());
						editRemark.clearFocus();
						editRemark.setFocusable(false);
						editRemark.setFocusableInTouchMode(false);
					}
				}
			});
			saveEdit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					editRemarkLayout.setVisibility(View.INVISIBLE);
					SysCall.hideSoftInput(editRemarkLayout, getActivity());
					editRemark.clearFocus();
					editRemark.setFocusable(false);
					editRemark.setFocusableInTouchMode(false);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					String date = sdf.format(calendar.getTime());
					SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
					DataConstants.dbHelper.updateCourseRecordByPhotoName(getActivity(), db, tableName,getResources().getString(R.string.dbcol_remark), editRemark.getText().toString(), photoNames.get(index));
					//db.close();
				}
			});
			ImageView rotateImg=(ImageView)rootView.findViewById(R.id.rotate_img);
			rotateImg.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Picasso.with(getActivity()).load(new File(reviewImgPaths.get(index))).rotate(90).into(reviewImg);
				}
			});
		}
		return rootView;
	}
	private void jumpToCompleteFragment()
	{
		Fragment fragment=new TaskCompleteFragment();
//		Bundle bundle = new Bundle();  
//        bundle.putString("type", "");  
//        fragment.setArguments(bundle);
		FragmentManager fm=getActivity().getSupportFragmentManager();
		FragmentTransaction trans = fm.beginTransaction();  
		trans.replace(R.id.exercise_frame, fragment);
		trans.commit();
	}
	class CourseMasterListener implements OnClickListener
	{

		String photoName;
		String tableName;
		String masterState;
		public CourseMasterListener(String photoName, String tableName,
				String masterState) {
			super();
			this.photoName = photoName;
			this.tableName = tableName;
			this.masterState = masterState;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		
				//rootView.startAnimation(animation);
				DataConstants.dbHelper.updateCourseRecordByPhotoName(getActivity(), db, tableName,getResources().getString(R.string.dbcol_master_state), masterState, photoName);
				index++;
				if(index==reviewImgPaths.size())
				{
					jumpToCompleteFragment();
				}
				else
				Picasso.with(getActivity()).load(new File(reviewImgPaths.get(index))).into(reviewImg);
				
			}

		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		db = DataConstants.dbHelper.getReadableDatabase();
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		db.close();
	}
//	private void choosePaths(String fileDir)
//	{
//		Log.i("flip","dir "+fileDir);
//		File dir=new File(fileDir);
//		if(dir.exists())
//		{
//		//	Log.i("flip","dir exist");
//		}
//		File[] tempList = dir.listFiles();
//		//Log.i("flip","len "+tempList.length);
//		for (int i = 0; i < tempList.length; i++) 
//		{
//			   if (tempList[i].isFile()) 
//			   {
//				   reviewImgPaths.add(tempList[i].getPath());
//			//   Log.i("flip","path"+tempList[i].getPath());
//			   }
//		}
//	}
}
