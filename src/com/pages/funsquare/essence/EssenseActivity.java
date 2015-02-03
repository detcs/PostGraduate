package com.pages.funsquare.essence;

import com.app.ydd.R;
import com.data.model.EssenseDetail;
import com.data.util.NetCall;
import com.data.util.NetCall.PullEssenseDetail;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class EssenseActivity extends Activity implements EssenseJump,
		PullEssenseDetail {
	// private static final String TAG = "EssenseActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_essense);
		init();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	// EssenseJump
	@Override
	public void detail(String id) {
		// TODO Auto-generated method stub
		NetCall.getEssenseDetail(id, this);
	}

	// *************init*************
	private void init() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		EssenseFragment essenseFragment = new EssenseFragment();
		transaction.replace(R.id.FrameLayout1, essenseFragment);
		transaction.commit();
	}

	// NetCall:PullEssenseDetail
	@Override
	public void pullEDSuccess(EssenseDetail ed) {
		// TODO Auto-generated method stub
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		EssenseDetailFragment detailFragment = new EssenseDetailFragment(ed);
		transaction.replace(R.id.FrameLayout1, detailFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	// NetCall:PullEssenseDetail
	@Override
	public void pullEDFail() {
		// TODO Auto-generated method stub

	}
}
