package com.pages.notes;

import java.util.ArrayList;
import java.util.List;

import com.example.testviewpager2.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class FootPrintActivity extends FragmentActivity{

	 ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_footprint);
		viewPager=(ViewPager)findViewById(R.id.footprint_viewpager);
		List<Fragment> fraList=new ArrayList<Fragment>();
		for(int i=0;i<3;i++)
		fraList.add(new FootPrintFragment());
		viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fraList));
		viewPager.setCurrentItem(3);
	}
	
	public class MyViewPagerAdapter extends FragmentPagerAdapter{
		
		List<Fragment> fragmentList;
		public MyViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
			super(fm);
			fragmentList=list;
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}
	}
		

		
}
