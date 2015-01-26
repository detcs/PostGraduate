package com.pages.funsquare;

import java.util.ArrayList;
import java.util.List;

import com.example.testviewpager2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class FunctionsSquareFragment extends Fragment{

	GridView funcGridView;
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_funcs_gird, container, false);

		funcGridView=(GridView)rootView.findViewById(R.id.funcs_grid);
		List<String> names=new ArrayList<String>();
		names.add("骞垮??");names.add("绮惧??");	names.add("vip");names.add("???绯绘??浠?");
		funcGridView.setAdapter(new ButtonsGridViewAdapter(names,getActivity()));
		
		return rootView;
	}
}
