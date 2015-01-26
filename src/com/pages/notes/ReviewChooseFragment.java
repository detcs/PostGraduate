package com.pages.notes;

import java.util.ArrayList;
import java.util.List;

import com.example.testviewpager2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

public class ReviewChooseFragment extends Fragment{

	Button reviewReverse;
	Button reviewEbbin;
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_reviewchoose, container, false);
		
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
	
}
