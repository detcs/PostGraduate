package com.pages.login;



import com.app.ydd.R;
import com.view.util.FragmentActionListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PhoneVerifyFragment extends Fragment {

	private FragmentActionListener mListener = null;
	EditText phone;
	Button obtainVerifyNum; 
	EditText verifyNum;
	Button nextStep;
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
		View rootView=inflater.inflate(R.layout.fragment_phone_verify, null);
		phone=(EditText)rootView.findViewById(R.id.phone);
		verifyNum=(EditText)rootView.findViewById(R.id.verify_num);
		obtainVerifyNum=(Button)rootView.findViewById(R.id.get_verify_num);
		nextStep=(Button)rootView.findViewById(R.id.next_step);
		nextStep.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mListener.switchToNext();
			}
		});
		return rootView;
	}

}
