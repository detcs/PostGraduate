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
import com.pages.funsquare.square.SquareFragment;
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
import android.widget.BaseAdapter;
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
	FragmentManager fm;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// wsy 2015/2/3
		GloableData.init(getApplicationContext());

		LayoutInflater mInflater = getLayoutInflater();		
		fragList.add(new TodayFragment());
		fragList.add(new NoteFragment());
		fragList.add(new FunctionsSquareFragment());
		viewPager = (com.mobovip.views.DirectionalViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),fragList));
		//viewPager.setAdapter(new MyPagerAdapter(listViews));
		viewPager.setOrientation(com.mobovip.views.DirectionalViewPager.VERTICAL);
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
	        

			public List<Fragment> mFraList;
			public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> mFraList) {
				super(fm);
				this.mFraList=mFraList;
				// TODO Auto-generated constructor stub
			}
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mFraList.get(arg0);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mFraList.size();
			}

	       
	    }

	@Override
	protected void onDestroy() {
		if (mp.isPlaying()) {
			mp.stop();
		}
		mp.release();
		super.onDestroy();
	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==DataConstants.RESULTCODE_COURSE_SETTING)
		{
			//ListView courseNamelist = (ListView) listViews.get(1).findViewById(R.id.course_list);
			//BaseAdapter ba=(BaseAdapter) courseNamelist.getAdapter();
			//NotesClassAdapter(ba);
		}
	}
	

}
