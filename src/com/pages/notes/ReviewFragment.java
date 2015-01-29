package com.pages.notes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.testviewpager2.R;
import com.squareup.picasso.Picasso;

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
import android.widget.ImageView;

public class ReviewFragment extends Fragment{

	Button leftBtn;
	Button rightBtn;
	ImageView reviewImg;
	List<String> reviewImgPaths;
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
			 reviewImgPaths=new ArrayList<String>();
			//choosePaths(getSDPath()+"/DCIM/Camera");
			choosePaths("/storage/sdcard1/DCIM/Camera");
			Picasso.with(getActivity()).load(new File(reviewImgPaths.get(0))).into(reviewImg);
			leftBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(index==reviewImgPaths.size())
					{
						jumpToCompleteFragment();
					}
					else
					{
						Picasso.with(getActivity()).load(new File(reviewImgPaths.get(index))).into(reviewImg);
						index++;
					}
				}
			});
			rightBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(index==reviewImgPaths.size())
					{
						jumpToCompleteFragment();
					}
					else
					{
						Picasso.with(getActivity()).load(new File(reviewImgPaths.get(index))).into(reviewImg);
						index++;
					}
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
	private void choosePaths(String fileDir)
	{
		Log.i("flip","dir "+fileDir);
		File dir=new File(fileDir);
		if(dir.exists())
		{
		//	Log.i("flip","dir exist");
		}
		File[] tempList = dir.listFiles();
		//Log.i("flip","len "+tempList.length);
		for (int i = 0; i < tempList.length; i++) 
		{
			   if (tempList[i].isFile()) 
			   {
				   reviewImgPaths.add(tempList[i].getPath());
			//   Log.i("flip","path"+tempList[i].getPath());
			   }
		}
	}
}
