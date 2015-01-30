package com.pages.funsquare.essence;

import com.app.ydd.R;
import com.view.util.ViewGenerator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class EssenseActivity extends FragmentActivity implements
		EssenseFragment.ShowDetail {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_essense);
		showEssense();
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

	@Override
	public void switchToDetail(ViewGenerator viewGenerator) {
		// TODO Auto-generated method stub
		// todo
		EssenseDetailFragment fragment = new EssenseDetailFragment();
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	// **********for fragment Switch**********

	private void showEssense() {
		EssenseFragment fragment = new EssenseFragment();
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment).commit();
	}
}
