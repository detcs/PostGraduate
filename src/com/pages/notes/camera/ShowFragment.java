package com.pages.notes.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.app.ydd.R;
import com.data.model.CourseRecordInfo;
import com.data.model.DataConstants;
import com.data.model.DatabaseHelper;
import com.data.model.FileDataHandler;
import com.data.model.UserConfigs;
import com.data.util.SysCall;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShowFragment extends Fragment {
	// private static final String TAG = "ShowFragment";
	private View rootView;
	private Bitmap bitmap;
	private ShowJump jump;
	private List<String> items = UserConfigs.getCourseNames();
	private String finalRemarks = "";

	private TextView againView;
	private TextView editView;// click you can edit

	private LinearLayout hideBar;// hide
	private RelativeLayout hideChoose;
	private TextView quitView;
	private TextView saveView;
	private EditText remarksView;// is empty before edit

	private RadioGroup gendergroup;
	private RadioButton[] radios = new RadioButton[4];
	private Button saveBu;
	private ImageView imageView1;
	int selectIndex=0;
	public void setBitmap(Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		this.bitmap = bitmap;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_show, container,
					false);
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

	// ****************init****************
	private void init(View view) {
		findVeiws(view);
		initImage();
		setListener();
	}

	private void findVeiws(View view) {
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);

		againView = (TextView) view.findViewById(R.id.againView);
		editView = (TextView) view.findViewById(R.id.editView);

		hideBar = (LinearLayout) view.findViewById(R.id.hideBar);
		hideChoose = (RelativeLayout) view.findViewById(R.id.hideChoose);
		quitView = (TextView) view.findViewById(R.id.quitView);
		saveView = (TextView) view.findViewById(R.id.saveView);
		remarksView = (EditText) view.findViewById(R.id.remarksView);

		gendergroup = (RadioGroup) view.findViewById(R.id.gendergroup);
		radios[0] = (RadioButton) view.findViewById(R.id.radioButton1);
		radios[1] = (RadioButton) view.findViewById(R.id.radioButton2);
		radios[2] = (RadioButton) view.findViewById(R.id.radioButton3);
		radios[3] = (RadioButton) view.findViewById(R.id.radioButton4);
		
		saveBu = (Button) view.findViewById(R.id.saveBu);
	}

	private void initImage() {
		imageView1.setImageBitmap(CameraUtil.scaleToScreen(getActivity(),
				bitmap));
		for (int i = 0; i < 4; i++) {
			radios[i].setText(items.get(i));
		}
	}

	private void setListener() {
		againView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump.take();
			}
		});

		editView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String remarks = remarksView.getText().toString();
				if (remarks.trim().equals("")) {
					showRemark();
				} else {
					// change remarks
					canEdit();
				}
			}
		});

		quitView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hideRemark();
			}
		});
		gendergroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				for(int i=0;i<4;i++)
				{
					if(arg1==radios[i].getId())
					{
						selectIndex=i;break;
					}
				}
				saveBu.setOnClickListener(new SaveListener(selectIndex));
				Log.e(DataConstants.TAG,"radio "+selectIndex);
			}
		});
		saveView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String remarks = remarksView.getText().toString().trim();
				// no mater whether has remarks: do as has remarks
				finalRemarks = remarks;
				editView.setText("修改备注");
				hideChoose.setVisibility(View.INVISIBLE);
				// remarksView.clearFocus();这样还是可以获得焦点的
				cannotEdit();
			}
		});

		
	}

	private void canEdit() {
		hideChoose.setVisibility(View.VISIBLE);
		SysCall.bumpSoftInput(remarksView, getActivity());
	}

	@SuppressLint("ResourceAsColor")
	private void cannotEdit() {
		SysCall.hideSoftInput(rootView, getActivity());
		remarksView.clearFocus();
		remarksView.setFocusable(false);
		remarksView.setFocusableInTouchMode(false);
		// remarksView.setBackgroundColor(R.color.camera_float_bar);
	}

	private void showRemark() {
		hideBar.setVisibility(View.VISIBLE);
		SysCall.bumpSoftInput(remarksView, getActivity());
	}

	private void hideRemark() {
		remarksView.setText(finalRemarks);
		if (finalRemarks.equals("")) {
			hideBar.setVisibility(View.INVISIBLE);
		} else {
			hideChoose.setVisibility(View.INVISIBLE);
			cannotEdit();
		}
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
	class SaveListener implements OnClickListener
	{

		int index;
		
		public SaveListener(int index) {
			super();
			this.index = index;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(!FileDataHandler.sdCardExist())// no sd card
			{
				
			}
			else
			{
				String storePath=FileDataHandler.APP_DIR_PATH+"/";
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd|HH:mm:ss");
				String time=sdf.format(new Date());
				//test date
				sdf=new  SimpleDateFormat("yyyy_MM_dd");
				String date=sdf.format(new Date());
				//test date
				String photoName=UserConfigs.getAccount()+"|"+time+".jpg";
				try 
				{
					
					
					String tableName=null;
					if(index==0)
					{
						tableName=getResources().getString(R.string.db_english_table);
						storePath+=getResources().getString(R.string.dir_english);
					}
					else if(index==1)
					{
						tableName=getResources().getString(R.string.db_politics_table);
						storePath+=getResources().getString(R.string.dir_politics);
					}
					else if(index==2)
					{
						if(UserConfigs.getCourseMathName()!=null)
						{
							tableName=getResources().getString(R.string.db_math_table);
							storePath+=getResources().getString(R.string.dir_math);
						}
						else
						{	
							tableName=getResources().getString(R.string.db_profess1_table);
							storePath+=getResources().getString(R.string.dir_profess1);
						}
					}
					else if(index==3)
					{
						if(UserConfigs.getCourseProfessOneName()==null)
						{
							tableName=getResources().getString(R.string.db_profess1_table);
							storePath+=getResources().getString(R.string.dir_profess1);
						}
						else
						{
							tableName=getResources().getString(R.string.db_profess2_table);
							storePath+=getResources().getString(R.string.dir_profess2);
						}
					}
					Log.e(DataConstants.TAG,"save "+storePath);
					saveMyBitmap(storePath+"/"+photoName);
					savePhotoRecordToDataBase(tableName,photoName, "photobase64", "remark",date, time, 0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	private void savePhotoRecordToDataBase(String tableName,String photoName,String photobase64,String remark,String date,String time,int flag)
	{
		DatabaseHelper dbHelper = DataConstants.dbHelper;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		CourseRecordInfo cri=new CourseRecordInfo(photoName, photobase64, remark, date, time, getResources().getString(R.string.state_unknow), getResources().getString(R.string.upload_no), flag);
		dbHelper.insertCourseRecord(getActivity(), db,tableName , cri);
		dbHelper.queryShowRecords(db, tableName);
		db.close();
	}
	// ****************interface call back****************
	public interface ShowJump {
		public void take();
	}
}
