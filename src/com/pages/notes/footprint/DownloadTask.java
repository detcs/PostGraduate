package com.pages.notes.footprint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.data.model.DataConstants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadTask extends AsyncTask<String, Integer, String>{
	Context context;
	String dirPath;
	String updateCol;
	String id;
	String date;
	public DownloadTask(Context context, String dirPath, String updateCol,String id,String date) {
		super();
		this.context = context;
		this.dirPath = dirPath;
		this.updateCol=updateCol;
		this.id=id;
	}
	/* @Desciption: 利用Http协议下载文件并存储到SDCard 
    1.创建一个URL对象 
    2.通过URL对象,创建一个HttpURLConnection对象 
    3.得到InputStream 
    4.从InputStream当中读取数据 
    存到SDCard 
    1.取得SDCard路径 
    2.利用读取大文件的IO读法，读取文件 
	 */
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String urlStr=DataConstants.DOWNLOAD_URL+id;  
		Log.e(DataConstants.TAG,"inback "+urlStr);
		 OutputStream output=null;
		 String fileName=null;
		try {  
            /* 
             * 通过URL取得HttpURLConnection 
             * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置 
             * <uses-permission android:name="android.permission.INTERNET" /> 
             */  
            URL url=new URL(urlStr);  
            HttpURLConnection conn=(HttpURLConnection)url.openConnection(); 
            conn.setRequestProperty("Accept-Encoding", "identity"); 
            //取得inputStream，并进行读取  
            InputStream input=conn.getInputStream(); 
            String nameInfo=conn.getHeaderField("Content-disposition");
            fileName=nameInfo.split("=")[1];
            Log.e(DataConstants.TAG,"filename:"+fileName);
            File file=new File(dirPath+"/"+fileName);
            file.createNewFile();
            output=new FileOutputStream(file);
            byte[] buffer=new byte[4*1024];  
//            while(input.read(buffer)!=-1){  
//                output.write(buffer);  
//            }  
            int temp;
            while ((temp = input.read(buffer)) != -1) {
                output.write(buffer, 0, temp);
               }
            output.flush();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  finally{  
            try {  
                output.close();  
               // System.out.println("success");  
            } catch (IOException e) {  
                //System.out.println("fail");  
                e.printStackTrace();  
            }  
    }  
		return fileName;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		 Log.e(DataConstants.TAG,"onpost:");
		//FootprintInfo fpInfo=new FootprintInfo(coverPicPath, coverSongPath, coverSongName, footprintPicPath, diary, date)
		 SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		DataConstants.dbHelper.updateFootprintRecord(context, db, updateCol, result, date);
	}
	
	
	
	

}
