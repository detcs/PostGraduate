package com.pages.funsquare.square;

import com.app.ydd.R;
import com.data.util.SysCall;
import com.view.util.AdapterFresh;
import com.view.util.InformAdapter;
import com.view.util.InformAdapter.ViewHolder;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SquareInformFragment extends Fragment {
	private static final String TAG = "SquareInformFragment";
	private View rootView;
	private SquareJump jump;
	private InformAdapter adapter;

	private Button backBu;
	private ListView listView1;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		jump = (SquareJump) activity;
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		adapter.fresh();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_square_inform,
					container, false);
		}
		init(rootView);
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "resume");
		((AdapterFresh) adapter).fresh();
	}

	// ****************init****************

	private void init(View view) {
		findViews(view);
		initListView();
		setListener();
	}

	private void findViews(View view) {
		backBu = (Button) view.findViewById(R.id.backBu);
		listView1 = (ListView) view.findViewById(R.id.hintListView);
	}

	private void initListView() {
		adapter = new InformAdapter(getActivity());
		listView1.setAdapter(adapter);
	}

	private void setListener() {
		backBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SysCall.clickBack();
			}
		});

		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ViewHolder holder = (ViewHolder) view.getTag();
				String pid = holder.pid;
				jump.detail(pid);
			}
		});
	}
}
