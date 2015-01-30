package com.pages.notes;

import com.app.ydd.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ClockFragment extends Fragment {

	TextView clockDays;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_clock, container, false);
		clockDays=(TextView)rootView.findViewById(R.id.clock_days);
		clockDays.setText(getResources().getString(R.string.clock)+"1"+getResources().getString(R.string.day));
		return rootView;
	}
}
