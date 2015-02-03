package com.pages.funsquare.square;

import com.app.ydd.R;
import com.data.model.Post;
import com.data.util.NetCall;
import com.data.util.NetCall.PullViewGenerator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class SquareActivity extends Activity implements SquareJump,
		PullViewGenerator {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_square);
		showSquare();
	}

	// SquareJump
	@Override
	public void publish() {
		// TODO Auto-generated method stub
		SquarePublishFragment fragment = new SquarePublishFragment();
		jumpTo(fragment);
	}

	@Override
	public void detail(Post vg) {
		// TODO Auto-generated method stub
		SquareDetailFragment fragment = new SquareDetailFragment(vg);
		jumpTo(fragment);
	}

	@Override
	public void detail(String pid) {
		// TODO Auto-generated method stub
		NetCall.getPost(pid, this);
	}

	@Override
	public void inform() {
		// TODO Auto-generated method stub
		SquareInformFragment fragment = new SquareInformFragment();
		jumpTo(fragment);
	}

	private void jumpTo(Fragment fragment) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	// PullViewGenerator
	@Override
	public void pullVGFail() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pullVGSuccess(Post vg) {
		// TODO Auto-generated method stub
		detail(vg);
	}

	// ***************init***************

	private void showSquare() {
		Fragment fragment = new SquareFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.FrameLayout1, fragment);
		// transaction.addToBackStack(null);//此处不该有，因为本身是想看上去像一个界面
		transaction.commit();
	}
}
