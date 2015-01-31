package com.pages.notes.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.ydd.R;
import com.data.model.DataConstants;
import com.data.model.DatabaseHelper;
import com.data.model.UserConfigs;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowFragment extends Fragment {
	private View rootView;
	private Bitmap bitmap;
	private ShowJump jump;
	private TextView againView;
	private Button saveBu;
	private ImageView imageView1;
	
	public void setBitmap(Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		this.bitmap=bitmap;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_show, container, false);
		}
		init(rootView);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof ShowJump)) {
			throw new IllegalStateException("error");
		}
		jump = (ShowJump) activity;
	}
	
//	****************init****************
	private void init(View view){
		findVeiws(view);
		initImage();
		setListener();
	}
	private void findVeiws(View view){
		againView=(TextView)view.findViewById(R.id.againView);
		saveBu=(Button)view.findViewById(R.id.saveBu);
		imageView1=(ImageView)view.findViewById(R.id.imageView1);
	}
	private void initImage(){
		imageView1.setImageBitmap(bitmap);
	}
	private void setListener(){
		againView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump.take();
			}
		});
		saveBu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(DataConstants.SD_PATH==null)// no sd card
				{
					
				}
				else
				{
					String storePath=DataConstants.SD_PATH+"/"+DataConstants.PHOTO_DIR_PATH+"/"+getResources().getString(R.string.english_dir);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd|HH:mm:ss");
					String time=sdf.format(new Date());
					//test date
					sdf=new  SimpleDateFormat("yyyy_MM_dd");
					String date=sdf.format(new Date());
					//test date
					String photoName=UserConfigs.getAccount()+"|"+time+".jpg";
					try 
					{
						Log.e(DataConstants.TAG,"save "+storePath);
						saveMyBitmap(storePath+"/"+photoName);
						savePhotoRecordToDataBase(photoName, "photobase64", "remark",date, time, 0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	public void saveMyBitmap(String path) throws IOException {
        File f = new File(path);
        f.createNewFile();
        FileOutputStream fOut = null;
        try {
                fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
                fOut.flush();
        } catch (IOException e) {
                e.printStackTrace();
        }
        try {
                fOut.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
}
	private void savePhotoRecordToDataBase(String photoName,String photobase64,String remark,String date,String time,int flag)
	{
		DatabaseHelper dbHelper = DataConstants.dbHelper;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String tableName=getResources().getString(R.string.db_english_table);
		dbHelper.insertCourseRecord(getActivity(), db,tableName , photoName, photobase64, remark,date, time, flag);
		dbHelper.queryShowRecords(db, tableName);
		db.close();
	}

//	****************interface call back****************
	public interface ShowJump{
		public void take();
	}
}
