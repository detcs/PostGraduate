package com.pages.notes.camera;

import com.app.ydd.R;
import com.data.util.SysCall;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShowFragment extends Fragment {
	// private static final String TAG = "ShowFragment";
	private View rootView;
	private Bitmap bitmap;
	private ShowJump jump;
	private String[] items = { "英語1", "數學2", "政治", "物理" };
	private String finalRemarks = "";

	private TextView againView;
	private TextView editView;// click you can edit

	private LinearLayout hideBar;// hide
	private RelativeLayout hideChoose;
	private TextView quitView;
	private TextView saveView;
	private EditText remarksView;// is empty before edit

	// private RadioGroup gendergroup;
	private RadioButton[] radios = new RadioButton[4];
	private Button saveBu;
	private ImageView imageView1;

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

		// gendergroup = (RadioGroup) view.findViewById(R.id.gendergroup);
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
			radios[i].setText(items[i]);
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

		saveBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

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

	// ****************interface call back****************
	public interface ShowJump {
		public void take();
	}
}
