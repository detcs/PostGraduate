package com.pages.notes;

import java.util.ArrayList;
import java.util.List;

import com.app.ydd.R;
import com.pages.funsquare.ButtonsGridViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class NoteFragment  extends Fragment{
	GridView funcGridView;
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
		funcGridView=(GridView)rootView.findViewById(R.id.notes_grid);
		List<String> names=new ArrayList<String>();
		names.add(getResources().getString(R.string.english));
		names.add(getResources().getString(R.string.politics));	
		names.add(getResources().getString(R.string.math));
		names.add(getResources().getString(R.string.professional_course));	
		
		funcGridView.setAdapter(new ButtonsGridViewAdapter(names,getActivity()));
		
		return rootView;
	}
	
	

	

}
