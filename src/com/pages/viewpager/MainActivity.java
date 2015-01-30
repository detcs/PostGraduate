package com.pages.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.app.ydd.R;
import com.pages.funsquare.ButtonsGridViewAdapter;
import com.pages.funsquare.FunctionsSquareFragment;
import com.pages.notes.CourseSetting;
import com.pages.notes.ExerciseActivity;
import com.pages.notes.FootPrintActivity;
import com.pages.notes.NoteFragment;
import com.pages.notes.NotesClassAdapter;
import com.pages.notes.camera.CameraActivity;
import com.pages.today.TodayFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private com.mobovip.views.DirectionalViewPager viewPager;
	final ArrayList<Fragment> fragList = new ArrayList<Fragment>();
	private int[] ids=new int[]{R.drawable.biz_ad_new_version1_img0,R.drawable.biz_ad_new_version1_img1,R.drawable.biz_ad_new_version1_img2,R.drawable.biz_ad_new_version1_img3};
	FragmentManager fm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ArrayList<View> listViews = new ArrayList<View>();
		
		LayoutInflater mInflater = getLayoutInflater();
		for(int i=0;i<ids.length;i++){
			//listViews.add(mInflater.inflate(R.layout.fragment_layout, null));
		}
		listViews.add(mInflater.inflate(R.layout.fragment_today, null));
		listViews.add(mInflater.inflate(R.layout.fragment_notes, null));
		listViews.add(mInflater.inflate(R.layout.fragment_funcs_gird, null));
		final View btn=findViewById(R.id.btn);
		
		viewPager=(com.mobovip.views.DirectionalViewPager)findViewById(R.id.viewPager);
		//viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
		viewPager.setAdapter(new MyPagerAdapter(listViews));
		viewPager.setOrientation(com.mobovip.views.DirectionalViewPager.VERTICAL);
		viewPager.setSaveEnabled(false);//else nullpoint when jump
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				//btn.setVisibility(position==listViews.size()-1?View.VISIBLE:View.GONE);
				if(position==1)//note
	        	{
	        		
	        	}
	            
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
	public class MyFragmentPagerAdapter extends FragmentPagerAdapter
	{

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
				// ((ViewPager) arg0).removeViewAt(arg1);//.removeView(mFragList.get(arg1));
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
	        	View v=mListViews.get(position);
	        	Log.i("flip"," v==null?"+(v==null));
//	        	ImageView iv=(ImageView)v.findViewById(R.id.iv);
//	        	iv.setImageResource(ids[position]);
	        	((ViewPager) view).addView(v, 0);
	        	if(position==0)//todayfragment
	        	{
	        		
	        	}
	        	else if(position==1)//notefragment
	        	{
	        		initNoteView(v);
	        	}
	        	else if(position==2)
	        	{
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
	        	Log.i("flip","Parcelable saveState()");
	            return null;
	        }

	        @Override
	        public void startUpdate(View arg0) {
	        }
	    }
	public void initNoteView(View v)
	{
		TextView diary=(TextView)v.findViewById(R.id.diary);
		diary.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final EditText inputServer = new EditText(MainActivity.this);
				inputServer.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		        builder.setTitle("Server").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
		                .setNegativeButton("Cancel", null);
		        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

		            public void onClick(DialogInterface dialog, int which) {
		               inputServer.getText().toString();
		             }
		        });
		        builder.show();
			}
		});
		
		TextView todayRec=(TextView)v.findViewById(R.id.today_rec);
		todayRec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, ExerciseActivity.class);
				intent.putExtra("tag", getResources().getString(R.string.today_rec));
				startActivity(intent);
			}
		});
		Button takePhoto=(Button)v.findViewById(R.id.take_photo);
		
		takePhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("filp",""+arg0.getId()+" button 1");
				
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, CameraActivity.class);
				startActivity(intent);
				
			}
		});
		GridView gv=(GridView)v.findViewById(R.id.notes_grid);
		List<String> names=new ArrayList<String>();
		names.add(getResources().getString(R.string.english));
		names.add(getResources().getString(R.string.politics));	
		names.add(getResources().getString(R.string.math));
		names.add(getResources().getString(R.string.professional_course));	
		gv.setAdapter(new NotesClassAdapter(names, MainActivity.this));
		TextView myFootPrint=(TextView)v.findViewById(R.id.my_footprint);
		myFootPrint.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, FootPrintActivity.class);
				startActivity(intent);
			}
		});
	}
	public void initFunctionsSquareView(View v)
	{
		
		GridView gv=(GridView)v.findViewById(R.id.funcs_grid);
		List<String> names=new ArrayList<String>();
		names.add(getResources().getString(R.string.essence));
		names.add(getResources().getString(R.string.square));	
		names.add(getResources().getString(R.string.math));
		names.add(getResources().getString(R.string.backup));	
		gv.setAdapter(new ButtonsGridViewAdapter(names, MainActivity.this));
	}
	 

}
