package com.pages.login;

import com.app.ydd.R;
import com.view.util.FragmentActionListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AccountSettingFragment extends Fragment{

	private FragmentActionListener mListener = null;
	@Override
	public void onAttach(Activity activity) {
		try {
			mListener = (FragmentActionListener) activity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_account_setting, null);
		return rootView;
	}
}
