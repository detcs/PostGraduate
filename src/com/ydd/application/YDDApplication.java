package com.ydd.application;

import com.data.model.DataConstants;
import com.data.model.UserConfigs;
import com.pages.login.LoginActivity;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class YDDApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e(DataConstants.TAG,"app create");
		initUserConfig();
		
	}
	private void initUserConfig()
	{
		UserConfigs uc=new UserConfigs(getApplicationContext());
	}
	
}
