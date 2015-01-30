package com.pages.funsquare.square;



import com.app.ydd.R;
import com.pages.funsquare.square.SquareFragment.PubOrDetail;
import com.pages.funsquare.square.SquareInformFragment.InformDetail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class SquareActivity extends FragmentActivity implements PubOrDetail,
		InformDetail {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_square);
		showSquare();
	}

	// 从广场第一页向其他页面跳转
	@Override
	public void publish() {
		// TODO Auto-generated method stub
		SquarePublishFragment fragment = new SquarePublishFragment();
		jumpTo(fragment);
	}

	@Override
	public void detail(int index) {
		// TODO Auto-generated method stub
		SquareDetailFragment fragment = new SquareDetailFragment();
		jumpTo(fragment);
	}

	@Override
	public void inform() {
		// TODO Auto-generated method stub
		SquareInformFragment fragment = new SquareInformFragment();
		jumpTo(fragment);
	}

	private void jumpTo(Fragment fragment) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	// 从广场消息提醒界面向其他界面跳转
	@Override
	public void checkInformDetail(int index) {
		// TODO Auto-generated method stub
		detail(index);
	}

	// ***************init***************

	private void showSquare() {
		Fragment fragment = new SquareFragment();
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment).commit();
	}

}
