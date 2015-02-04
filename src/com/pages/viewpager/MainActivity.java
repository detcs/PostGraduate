package com.pages.viewpager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.ydd.R;
import com.data.model.DataConstants;
import com.data.model.FileDataHandler;
import com.data.model.NormalPostRequest;
import com.data.model.UserConfigs;
import com.data.util.GloableData;
import com.data.util.SysCall;
import com.pages.funsquare.ButtonsGridViewAdapter;
import com.pages.funsquare.FunctionsSquareFragment;
import com.pages.login.LoginActivity;
import com.pages.notes.CourseSetting;
import com.pages.notes.ExerciseActivity;
import com.pages.notes.NoteFragment;
import com.pages.notes.NotesClassAdapter;
import com.pages.notes.camera.CameraActivity;
import com.pages.notes.footprint.DownloadTask;
import com.pages.notes.footprint.FootPrintActivity;
import com.pages.notes.footprint.FootprintInfo;
import com.pages.today.TodayFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private com.mobovip.views.DirectionalViewPager viewPager;
	final ArrayList<Fragment> fragList = new ArrayList<Fragment>();
	// private int[] ids=new
	// int[]{R.drawable.biz_ad_new_version1_img0,R.drawable.biz_ad_new_version1_img1,R.drawable.biz_ad_new_version1_img2,R.drawable.biz_ad_new_version1_img3};
	ArrayList<View> listViews;
	FragmentManager fm;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// wsy 2015/2/3
		GloableData.init(getApplicationContext());

		listViews = new ArrayList<View>();

		LayoutInflater mInflater = getLayoutInflater();

		listViews.add(mInflater.inflate(R.layout.fragment_today, null));
		listViews.add(mInflater.inflate(R.layout.fragment_notes, null));
		listViews.add(mInflater.inflate(R.layout.fragment_funcs_gird, null));
		final View btn = findViewById(R.id.btn);

		viewPager = (com.mobovip.views.DirectionalViewPager) findViewById(R.id.viewPager);
		// viewPager.setAdapter(new
		// MyFragmentPagerAdapter(getSupportFragmentManager()));
		viewPager.setAdapter(new MyPagerAdapter(listViews));
		viewPager
				.setOrientation(com.mobovip.views.DirectionalViewPager.VERTICAL);
		viewPager.setSaveEnabled(false);// else nullpoint when jump
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// btn.setVisibility(position==listViews.size()-1?View.VISIBLE:View.GONE);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragList.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragList.size();
		}

	}

	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		// public List<Fragment> mFragList;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
			// ((ViewPager)
			// arg0).removeViewAt(arg1);//.removeView(mFragList.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View view, int position) {
			View v = mListViews.get(position);
			Log.i("flip", " v==null?" + (v == null));
			// ImageView iv=(ImageView)v.findViewById(R.id.iv);
			// iv.setImageResource(ids[position]);
			((ViewPager) view).addView(v, 0);
			if (position == 0)// todayfragment
			{
				initTodayView(v);
			} else if (position == 1)// notefragment
			{
				initNoteView(v);
			} else if (position == 2) {
				initFunctionsSquareView(v);
			}
			return v;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			Log.i("flip", "Parcelable saveState()");
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	public void initTodayView(View v) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		// calendar.roll(Calendar.DAY_OF_YEAR,1);//tomorrow
		String date = sdf.format(calendar.getTime());
		// Log.e(DataConstants.TAG,"date:"+date);

		// requestFirstPageJasonInfo(getFirstPageURL(date),date);

		mp = MediaPlayer.create(this, R.raw.song);
		final ImageView play = (ImageView) v.findViewById(R.id.music_play);
		play.setImageResource(R.drawable.play);
		TextView musicName = (TextView) v.findViewById(R.id.music_name);
		musicName.setText("可惜没如果");
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mp.isPlaying()) {
					play.setImageResource(R.drawable.play);
					mp.pause();
				} else {
					play.setImageResource(R.drawable.pause);
					mp.start();
				}
			}
		});

		// mp.start();
	}

	public void initNoteView(View v) {
		// final boolean
		// isFirstUse=UserConfigs.getIsFirstTakePhoto()==null?true:false;
		final LinearLayout editDiaryLayout=(LinearLayout)v.findViewById(R.id.diary_hideBar);
		final EditText editDiary = (EditText) v.findViewById(R.id.diary_remarksView);
		TextView cancelEdit=(TextView)v.findViewById(R.id.diary_quitView);
		TextView saveEdit=(TextView)v.findViewById(R.id.diary_saveView);
		TextView diary = (TextView) v.findViewById(R.id.diary);
		diary.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				editDiaryLayout.setVisibility(View.VISIBLE);
				SysCall.bumpSoftInput(editDiary, MainActivity.this);
			}
		});
		cancelEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(editDiaryLayout.getVisibility()==View.VISIBLE)
				{
					editDiaryLayout.setVisibility(View.INVISIBLE);
					SysCall.hideSoftInput(editDiaryLayout, MainActivity.this);
					editDiary.clearFocus();
					editDiary.setFocusable(false);
					editDiary.setFocusableInTouchMode(false);
				}
			}
		});
		saveEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				editDiaryLayout.setVisibility(View.INVISIBLE);
				SysCall.hideSoftInput(editDiaryLayout, MainActivity.this);
				editDiary.clearFocus();
				editDiary.setFocusable(false);
				editDiary.setFocusableInTouchMode(false);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				String date = sdf.format(calendar.getTime());
				SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
				DataConstants.dbHelper.updateFootprintRecord(getApplicationContext(), db, getResources().getString(R.string.dbcol_diary), editDiary.getText().toString(), date);
				db.close();
			}
		});
		TextView todayRec = (TextView) v.findViewById(R.id.today_rec);
		todayRec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ExerciseActivity.class);
				boolean isFirstUse = UserConfigs.getIsFirstTakePhoto() == null ? true
						: false;
				if (isFirstUse) {
					intent.putExtra("tag",getResources().getString(R.string.first_use));
					startActivityForResult(intent, 0);
				}
			   else {
					intent.putExtra("tag",getResources().getString(R.string.today_rec));
					startActivity(intent);
				}
				
			}
		});
		Button takePhoto = (Button) v.findViewById(R.id.take_photo);

		takePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("filp", "" + arg0.getId() + " button 1");

				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CameraActivity.class);
				startActivity(intent);

			}
		});
		// GridView gv=(GridView)v.findViewById(R.id.notes_grid);
		ListView courseNamelist = (ListView) v.findViewById(R.id.course_list);
		final List<String> courseTableNames=new ArrayList<>();
		final List<String> names = new ArrayList<String>();
		names.add(getResources().getString(R.string.english)+UserConfigs.getCourseEnglishName());
		courseTableNames.add(getResources().getString(R.string.db_english_table));
		names.add(getResources().getString(R.string.politics));
		courseTableNames.add(getResources().getString(R.string.db_politics_table));
		if(UserConfigs.getCourseMathName()!=null)
		{
			names.add(getResources().getString(R.string.math)+UserConfigs.getCourseMathName());
			courseTableNames.add(getResources().getString(R.string.db_math_table));
		}
		names.add(UserConfigs.getCourseProfessOneName());
		courseTableNames.add(getResources().getString(R.string.db_profess1_table));
		if(UserConfigs.getCourseProfessTwoName()!=null)
		{
			names.add(UserConfigs.getCourseProfessTwoName());
			courseTableNames.add(getResources().getString(R.string.db_profess2_table));
		}
		
		courseNamelist.setAdapter(new NotesClassAdapter(names,
				MainActivity.this));
	
		courseNamelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ExerciseActivity.class);
				boolean isFirstUse = UserConfigs.getIsFirstTakePhoto() == null ? true
						: false;
				if (isFirstUse) {
					intent.putExtra("tag",getResources().getString(R.string.first_use));
					startActivityForResult(intent, 0);
				} else {
					intent.putExtra("course_table_name", courseTableNames.get(position));
					intent.putExtra("tag",getResources().getString(R.string.note_class));
					startActivity(intent);
				}
				
			}
		});
		TextView myFootPrint = (TextView) v.findViewById(R.id.my_footprint);
		myFootPrint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				boolean isFirstUse = UserConfigs.getIsFirstTakePhoto() == null ? true
						: false;
				if (isFirstUse) {
					intent.putExtra("tag",
							getResources().getString(R.string.first_use));
					intent.setClass(MainActivity.this, ExerciseActivity.class);
				} else {
					intent.setClass(MainActivity.this, FootPrintActivity.class);
				}
				startActivity(intent);
			}
		});
		TextView count_note=(TextView)v.findViewById(R.id.count_note);
		SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		int count=DataConstants.dbHelper.queryAllCourseRecordsCount(getApplicationContext(), db);
		db.close();
		count_note.setText(count+"");
	}

	public void initFunctionsSquareView(View v) {

		GridView gv = (GridView) v.findViewById(R.id.funcs_grid);
		List<String> names = new ArrayList<String>();
		names.add(getResources().getString(R.string.essence));
		names.add(getResources().getString(R.string.square));
		names.add(getResources().getString(R.string.math));
		names.add(getResources().getString(R.string.backup));
		gv.setAdapter(new ButtonsGridViewAdapter(names, MainActivity.this));
	}

	@Override
	protected void onDestroy() {
		if (mp.isPlaying()) {
			mp.stop();
		}
		mp.release();
		super.onDestroy();
	}

	private void requestFirstPageJasonInfo(String url, final String date) {
		final FootprintInfo fpInfo;
		RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e(DataConstants.TAG, "response=" + response);
						parseFirstPageInfo(response, date);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// tv_1.setText(arg0.toString());
						Log.i(DataConstants.TAG,
								"sorry,Error" + arg0.toString());
						// if (progressDialog.isShowing()
						// && progressDialog != null) {
						// progressDialog.dismiss();
						// }
					}
				});
		requestQueue.add(jsonObjectRequest);
		// return fpInfo;
	}

	private String getFirstPageURL(String date) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		BasicNameValuePair pair = new BasicNameValuePair("methodno", "MIndex");
		params.add(pair);
		pair = new BasicNameValuePair("device", "android");
		params.add(pair);
		pair = new BasicNameValuePair("deviceid", "1");
		params.add(pair);
		pair = new BasicNameValuePair("appid", "nju");
		params.add(pair);
		pair = new BasicNameValuePair("userid", UserConfigs.getId());
		params.add(pair);
		pair = new BasicNameValuePair("verify", UserConfigs.getVerify());
		params.add(pair);
		pair = new BasicNameValuePair("date", date);
		params.add(pair);
		String resultURL = DataConstants.SERVER_URL + "?";
		for (NameValuePair nvp : params) {
			resultURL += nvp.getName() + "=" + nvp.getValue() + "&";

		}
		Log.e(DataConstants.TAG, "fpage:" + resultURL);
		return resultURL;
	}

	private FootprintInfo parseFirstPageInfo(JSONObject job, String date) {
		FootprintInfo fpInfo = null;
		try {
			JSONObject data = job.getJSONObject("data");
			JSONArray indexs = data.getJSONArray("index_");
			DownloadTask fileDownloadTask;
			DownloadTask songDownloadTask;
			SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
			Log.e(DataConstants.TAG, "len:" + indexs.length() + "");

			for (int i = 0; i < indexs.length(); i++) {
				JSONObject info = indexs.getJSONObject(i);
				JSONArray musics = info.getJSONArray("music_");
				JSONObject music = musics.getJSONObject(0);// info.getJSONObject("music_");
				String songName = music.getString("title_");
				String songId = music.getString("file_");
				String imgId = info.getString("img_");
				String encourage = info.getString("content_");
				String days = info.getString("days_");
				String daysLeft = info.getString("daysLeft_");
				// downloadHandler(DataConstants.DOWNLOAD_URL+songId,
				// FileDataHandler.COVER_SONG_DIR_PATH+"/"+songName+".mp3");
				// downloadHandler(DataConstants.DOWNLOAD_URL+imgId,
				// FileDataHandler.COVER_PIC_DIR_PATH+"/"+imgId+".jpg");
				fileDownloadTask = new DownloadTask(this,
						FileDataHandler.COVER_PIC_DIR_PATH, getResources()
								.getString(R.string.dbcol_cover_pic), imgId,
						date);
				fileDownloadTask.execute();
				songDownloadTask = new DownloadTask(this,
						FileDataHandler.COVER_SONG_DIR_PATH, getResources()
								.getString(R.string.dbcol_cover_song), songId,
						date);
				songDownloadTask.execute();
				fpInfo = new FootprintInfo("", songName, "", "", date,
						encourage, days, daysLeft);
				TextView experienceTv = (TextView) listViews.get(0)
						.findViewById(R.id.experience);
				TextView encourageTv = (TextView) listViews.get(0)
						.findViewById(R.id.encourage);
				encourageTv.setText(encourage);
				DataConstants.dbHelper.insertFootprintInfoRecord(
						getApplicationContext(), db, fpInfo);
			}
			db.close();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fpInfo;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==DataConstants.RESULTCODE_COURSE_SETTING)
		{
			ListView courseNamelist = (ListView) listViews.get(1).findViewById(R.id.course_list);
			courseNamelist.invalidate();
		}
	}
	
	// public void downloadHandler(String url,String path )
	// {
	// FinalHttp fh = new FinalHttp();
	// //调用download方法开始下载
	// Log.e(DataConstants.TAG,"downloadurl:"+url);
	// HttpHandler handler = fh.download(url, //这里是下载的路径
	// path, //这是保存到本地的路径
	// new AjaxCallBack<File>() {
	// @Override
	// public void onLoading(long count, long current) {
	// // textView.setText("下载进度："+current+"/"+count);
	// }
	//
	// @Override
	// public void onSuccess(File t) {
	// //textView.setText(t==null?"null":t.getAbsoluteFile().toString());
	// Log.e(DataConstants.TAG,"success");
	// }
	//
	// });
	//
	// //调用stop()方法停止下载
	// // handler.stop();
	// }

}
