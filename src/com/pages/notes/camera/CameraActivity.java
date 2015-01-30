package com.pages.notes.camera;

import com.app.ydd.R;
import com.pages.notes.camera.ShowFragment.ShowJump;
import com.pages.notes.camera.TakeFragment.TakeJump;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

public class CameraActivity extends FragmentActivity implements TakeJump,ShowJump{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		toTake();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// *************switch fragment*************
	private void toTake() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		TakeFragment takeFragment=new TakeFragment();
		fragmentTransaction.replace(R.id.FrameLayout1, takeFragment);
		fragmentTransaction.commit();
	}
	
	private void toShow(Bitmap bitmap){
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		ShowFragment showFragment=new ShowFragment();
		showFragment.setBitmap(bitmap);
		fragmentTransaction.replace(R.id.FrameLayout1, showFragment);
		fragmentTransaction.commit();
	}

//	**************interface jump**************
	@Override
	public void show(Bitmap bitmap) {
		// TODO Auto-generated method stub
		toShow(bitmap);
	}

	@Override
	public void take() {
		// TODO Auto-generated method stub
		toTake();
	}
}
