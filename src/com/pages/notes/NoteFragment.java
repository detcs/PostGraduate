package com.pages.notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.app.ydd.R;
import com.data.model.DataConstants;
import com.data.model.UserConfigs;
import com.data.util.SysCall;
import com.pages.funsquare.ButtonsGridViewAdapter;
import com.pages.notes.camera.CameraActivity;
import com.pages.notes.footprint.FootPrintActivity;
import com.pages.viewpager.MainActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class NoteFragment  extends Fragment{
	GridView funcGridView;
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
		initNoteView(rootView);
		return rootView;
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
				SysCall.bumpSoftInput(editDiary, getActivity());
			}
		});
		cancelEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(editDiaryLayout.getVisibility()==View.VISIBLE)
				{
					editDiaryLayout.setVisibility(View.INVISIBLE);
					SysCall.hideSoftInput(editDiaryLayout, getActivity());
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
				SysCall.hideSoftInput(editDiaryLayout, getActivity());
				editDiary.clearFocus();
				editDiary.setFocusable(false);
				editDiary.setFocusableInTouchMode(false);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				String date = sdf.format(calendar.getTime());
				SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
				DataConstants.dbHelper.updateFootprintRecord(getActivity(), db, getResources().getString(R.string.dbcol_diary), editDiary.getText().toString(), date);
				db.close();
			}
		});
		TextView todayRec = (TextView) v.findViewById(R.id.today_rec);
		todayRec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), ExerciseActivity.class);
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
				intent.setClass(getActivity(), CameraActivity.class);
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
		
		courseNamelist.setAdapter(new NotesClassAdapter(names,courseTableNames,getActivity()));
		courseNamelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), ExerciseActivity.class);
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
					intent.setClass(getActivity(), ExerciseActivity.class);
				} else {
					intent.setClass(getActivity(), FootPrintActivity.class);
				}
				startActivity(intent);
			}
		});
		TextView count_note=(TextView)v.findViewById(R.id.count_note);
		SQLiteDatabase db = DataConstants.dbHelper.getReadableDatabase();
		int count=DataConstants.dbHelper.queryAllCourseRecordsCount(getActivity(), db);
		db.close();
		count_note.setText(count+"");
	}

	

}
