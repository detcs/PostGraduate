package com.pages.notes;



import com.example.testviewpager2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class ExerciseActivity extends FragmentActivity{

	Fragment fragment;
	FrameLayout frame;
	FragmentManager fm;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise);
		Intent intent=getIntent();
		String tag=intent.getStringExtra("tag");
		if(tag.equals(getResources().getString(R.string.today_rec)))
		{
			frame=(FrameLayout)findViewById(R.id.exercise_frame);
			fragment=new ReviewFragment();
			Bundle bundle = new Bundle();  
	        bundle.putString("type", getResources().getString(R.string.today_rec));  
	        fragment.setArguments(bundle);
			fm=getSupportFragmentManager();
			FragmentTransaction trans = fm.beginTransaction();  
			trans.replace(R.id.exercise_frame, fragment);
			trans.commit();
			
		}
		else
		{
			frame=(FrameLayout)findViewById(R.id.exercise_frame);
			fragment=new ReviewChooseFragment();
			Bundle bundle = new Bundle();  
	        bundle.putString("type", "");  
	        fragment.setArguments(bundle);
			fm=getSupportFragmentManager();
			FragmentTransaction trans = fm.beginTransaction();  
			trans.replace(R.id.exercise_frame, fragment);
			trans.commit();
		}
		
		//frame.
	}

	
	
}
