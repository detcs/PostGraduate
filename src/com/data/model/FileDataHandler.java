package com.data.model;

import com.app.ydd.R;

import android.content.Context;
import android.os.Environment;

public class FileDataHandler {
	public static String SD_PATH=null;
	public static String APP_DIR_PATH=null;
	public static String COVER_SONG_DIR_PATH=null;
	public static String COVER_PIC_DIR_PATH=null;
	public static String FOOTPRINT_PIC_DIR_PATH=null;

	public static void init(Context context)
	{
		if(sdCardExist())
		{
			SD_PATH=Environment.getExternalStorageDirectory().toString();//获取跟目录 
			APP_DIR_PATH=SD_PATH+"/"+context.getResources().getString(R.string.dir_app);
			COVER_PIC_DIR_PATH=APP_DIR_PATH+"/"+context.getResources().getString(R.string.dir_cover_pic);
			COVER_SONG_DIR_PATH=APP_DIR_PATH+"/"+context.getResources().getString(R.string.dir_cover_song);
			FOOTPRINT_PIC_DIR_PATH=APP_DIR_PATH+"/"+context.getResources().getString(R.string.dir_footprint_pic);
		}
	}
	public static boolean sdCardExist()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在 
	}
	

}
