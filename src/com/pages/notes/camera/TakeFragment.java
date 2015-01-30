package com.pages.notes.camera;




import com.app.ydd.R;
import com.pages.notes.camera.MySurfaceView.MyCallBack;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TakeFragment extends Fragment implements MyCallBack{
	private static final int TAKEFRAGMENT=100;
	private View rootView;
	private TakeJump jump;
	private Activity activity;
	private TextView closeView;
	private TextView pickView;
	private Button takeBu;
	private MySurfaceView surfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_take, container, false);
		}
		init( rootView);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity=activity;
		if (!(activity instanceof TakeJump)) {
			throw new IllegalStateException("error");
		}
		jump = (TakeJump) activity;
	}
//	************init************
	private void init(View view){
		findViews(view);
		setListener();
	}
	private void findViews(View view){
		closeView=(TextView)view.findViewById(R.id.closeView);
		pickView=(TextView)view.findViewById(R.id.pickView);
		takeBu=(Button)view.findViewById(R.id.takeBu);
		surfaceView=(MySurfaceView)view.findViewById(R.id.mySurfaceView);
	}
	private void setListener(){
		closeView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SysCall.clickBack();
			}
		});
		pickView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
				startActivityForResult(intent, TAKEFRAGMENT);
			}
		});
		takeBu.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {// ����ʱ�Զ��Խ�
					surfaceView.autoFocus();
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {// �ſ�������
					surfaceView.takePicture(TakeFragment.this);
				}
				return true;
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == TAKEFRAGMENT && resultCode == activity.RESULT_OK && data != null) {

			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = activity.getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			jump.show(BitmapFactory.decodeFile(picturePath));
		}
	}
	
//	************interface call back************
	public interface TakeJump{
		public void show(Bitmap bitmap);
	}

	@Override
	public void afterTake(Bitmap bitmap) {
		// TODO Auto-generated method stub
		jump.show(bitmap);
	}
}
