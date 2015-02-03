package com.pages.funsquare.square;

import com.app.ydd.R;
import com.data.util.NetCall;
import com.data.util.NetCall.UploadData;
import com.data.util.SysCall;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class SquarePublishFragment extends Fragment implements UploadData {
	private View rootView;

	private Button backBu;
	private Button publishBu;
	private EditText titleText;
	private EditText editText1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		SysCall.bumpSoftInput(titleText, getActivity());
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_square_publish,
					container, false);
		}
		init(rootView);
		return rootView;
	}

	private void init(View view) {
		findViews(view);
		setListener();
	}

	private void findViews(View view) {
		backBu = (Button) view.findViewById(R.id.backBu);
		titleText = (EditText) view.findViewById(R.id.titleText);
		editText1 = (EditText) view.findViewById(R.id.editText1);
		publishBu = (Button) view.findViewById(R.id.publishBu);
	}

	private void setListener() {
		backBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SysCall.clickBack();
			}
		});
		publishBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String title = titleText.getText().toString();
				String content = editText1.getText().toString();
				if (null == title || title.trim().equals("")) {
					SysCall.hint(getActivity(), "無效標題");
				} else {
					NetCall.post(title, content, SquarePublishFragment.this);
				}
			}
		});
	}

	@Override
	public void updateSuccess() {
		// TODO Auto-generated method stub
		SysCall.hint(getActivity(), "发布成功");
		InputMethodManager imm = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
		SysCall.clickBack();
	}

	@Override
	public void updatetFail() {
		// TODO Auto-generated method stub
		SysCall.hint(getActivity(), "发布失败");
	}
}
