package com.pages.notes.camera;

import com.app.ydd.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
				
			}
		});
	}
	
//	****************interface call back****************
	public interface ShowJump{
		public void take();
	}
}
