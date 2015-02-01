package com.ydd.application;

import java.io.File;
import java.util.zip.DataFormatException;

import com.app.ydd.R;
import com.data.model.DataConstants;
import com.data.model.DatabaseHelper;
import com.data.model.FileDataHandler;
import com.data.model.UserConfigs;
import com.pages.login.LoginActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class YDDApplication extends Application{

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e(DataConstants.TAG,"app create");
		initUserConfig();
		initAppDIr();
		//initSD();
		initDataBase();
		initScreenParam();
		
	}
	
	private void initAppDIr()
	{
		FileDataHandler.init(getApplicationContext());
		if(FileDataHandler.SD_PATH!=null)
		{
			File dir=new File(FileDataHandler.APP_DIR_PATH);
			if(!dir.exists())
				dir.mkdir();
			dir=new File(FileDataHandler.COVER_PIC_DIR_PATH);
			if(!dir.exists())
				dir.mkdir();
			dir=new File(FileDataHandler.COVER_SONG_DIR_PATH);
			if(!dir.exists())
				dir.mkdir();
			dir=new File(FileDataHandler.FOOTPRINT_PIC_DIR_PATH);
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
		DataConstants.dbHelper= new DatabaseHelper(this);//这段代码放到Activity类中才用this
		SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		//if(!DataConstants.dbHelper.tableIsExist(db, getResources().getString(R.string.db_footprint_table)))
			DataConstants.dbHelper.createFootprintTable(getApplicationContext(), db);
		db.close();
	}
	private void initScreenParam() 
	{
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		DataConstants.screenWidth = dm.widthPixels;
		DataConstants.screenHeight = dm.heightPixels;
		Log.e(DataConstants.TAG,"(w,h)"+dm.widthPixels+","+dm.heightPixels);
	}
}
