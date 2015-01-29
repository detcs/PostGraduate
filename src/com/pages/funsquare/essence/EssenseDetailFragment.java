package com.pages.funsquare.essence;

import com.example.testviewpager2.R;
import com.view.util.SysCall;
import com.view.util.ViewGenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EssenseDetailFragment extends Fragment {
//	public EssenseDetailFragment(ViewGenerator viewGenerator) {
//	}
	public EssenseDetailFragment() {
	}
	private Context context;
	private View rootView;
	private Button backBu;
	private Button downBu;
	private String emailAddress;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = getActivity();
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_essense_detail,
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
		downBu = (Button) view.findViewById(R.id.downBu);
	}

	private void setListener() {
		backBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SysCall.clickBack();
			}
		});
		downBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText inputServer = new EditText(context);
				inputServer.setFocusable(true);
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Input your email address:")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setView(inputServer).setNegativeButton("Cancel", null);
				builder.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								emailAddress = inputServer.getText().toString();
								Log.i("wsy", emailAddress);
							}
						});
				builder.show();
			}
		});
	}
}
