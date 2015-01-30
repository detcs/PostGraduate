package com.ydd.application;

import java.io.File;

import com.data.model.DataConstants;
import com.data.model.DatabaseHelper;
import com.data.model.UserConfigs;
import com.pages.login.LoginActivity;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class YDDApplication extends Application{

	//static SQLiteDatabase db = null;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e(DataConstants.TAG,"app create");
		initUserConfig();
		initSD();
		//initDataBase();
		
	}
	
	private void initSD()
	{
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在 
		if   (sdCardExist)      //如果SD卡存在，则获取跟目录
		{                               
			DataConstants.SD_PATH = Environment.getExternalStorageDirectory().toString();//获取跟目录 
			File dir=new File(DataConstants.SD_PATH+"/"+DataConstants.PHOTO_DIR_PATH);
			if(!dir.exists())
				dir.mkdir();
		}   
				
	}
	private void initUserConfig()
	{
		UserConfigs uc=new UserConfigs(getApplicationContext());
	}
	private void initDataBase() 
	{
		DatabaseHelper database = new DatabaseHelper(this);//这段代码放到Activity类中才用this
		SQLiteDatabase db = database.getWritableDatabase();
	}
}
