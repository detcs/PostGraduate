package com.pages.funsquare;

import java.util.ArrayList;
import java.util.List;

import com.app.ydd.R;
import com.pages.viewpager.MainActivity;

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
		initFunctionsSquareView(rootView);
		return rootView;
	}
	public void initFunctionsSquareView(View v) {

		GridView gv = (GridView) v.findViewById(R.id.funcs_grid);
		List<String> names = new ArrayList<String>();
		names.add(getResources().getString(R.string.essence));
		names.add(getResources().getString(R.string.square));
		names.add(getResources().getString(R.string.math));
		names.add(getResources().getString(R.string.backup));
		names.add(getResources().getString(R.string.personal_center));
		gv.setAdapter(new ButtonsGridViewAdapter(names, getActivity()));
	}
}
