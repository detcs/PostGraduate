package com.pages.notes;

import java.util.ArrayList;
import java.util.List;

import com.example.testviewpager2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ReviewFragment extends Fragment{

	Button leftBtn;
	Button rightBtn;
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_review, container, false);
		leftBtn=(Button)rootView.findViewById(R.id.master);
		rightBtn=(Button)rootView.findViewById(R.id.unmaster);
		Bundle bundle=getArguments();
		String type=bundle.getString("type");
		if(type.equals(getResources().getString(R.string.today_rec)))
		{
			leftBtn.setText(getResources().getString(R.string.get));
			rightBtn.setText(getResources().getString(R.string.collect));
		}
		else
		{
			
		}
		return rootView;
	}
}
