package com.pages.login;

import java.util.ArrayList;
import java.util.List;

import com.app.ydd.R;
import com.view.util.FragmentActionListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends FragmentActivity implements FragmentActionListener{

	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private int mCurrentStepIndex;
	Button backBtn;
	TextView centerText;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_register);
		backBtn=(Button)findViewById(R.id.left_btn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(mCurrentStepIndex==0)
					finish();
				switchToPrevious();
			}
		});
		centerText=(TextView)findViewById(R.id.center_text);
		
		mFragments.add(new PhoneVerifyFragment());
		mFragments.add(new AccountSettingFragment());
		mCurrentStepIndex = 0;
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.register_frame, mFragments.get(0));
		ft.commit();
	}
	/**
	 * 开始切换页
	 * @param index
	 */
	public void switchToFragment(int index) {
		
			
		Fragment fragment = mFragments.get(index);
		FragmentTransaction ft = getFragmentTransaction(index);
		
		mFragments.get(mCurrentStepIndex).onPause();
        if(fragment.isAdded()){
            fragment.onResume();
        } else {
            ft.add(R.id.register_frame, fragment);
        }
        showFragment(index);
        ft.commit();
	}
	/**
	 * 显示fragment
	 * @param index
	 */
	private void showFragment(int index){
		if(index==0)
		{
			centerText.setText(getResources().getString(R.string.register));
		}
		else if(index==1)
		{
			centerText.setText(getResources().getString(R.string.account_setting));
		}
        for(int i = 0; i < mFragments.size(); i++){
            Fragment fragment = mFragments.get(i);
            FragmentTransaction ft = getFragmentTransaction(index);

            if(index == i){
                ft.show(fragment);
            }else{
                ft.hide(fragment);
            }
            ft.commit();
        }
        mCurrentStepIndex = index;
    }
	/**
	 * 设置切换动画效果
	 * @param index
	 * @return
	 */
	private FragmentTransaction getFragmentTransaction(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(index > mCurrentStepIndex){
        	ft.setCustomAnimations(R.anim.anim_bottom_in, R.anim.anim_top_out);
        }else{
        	ft.setCustomAnimations(R.anim.anim_top_in, R.anim.anim_bottom_out);
        }
        return ft;
    }
	@Override
	public void switchToNext() {
		// TODO Auto-generated method stub
		if(mCurrentStepIndex == mFragments.size() - 1) 
			return;
		switchToFragment(mCurrentStepIndex + 1);
	}

	@Override
	public void switchToPrevious() {
		// TODO Auto-generated method stub
		if(mCurrentStepIndex == 0) 
			return;
		switchToFragment(mCurrentStepIndex - 1);
	}

}
