package com.pages.funsquare.square;


import com.app.ydd.R;
import com.data.model.Post;
import com.view.util.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SquareFragment extends Fragment {
	private View rootView;
	private ListView listView1;
	private Button backBu;
	private Button publishBu;
	private Button informBu;
	private MyAdapter postAdapter;
	private PubOrDetail pubOrDetail;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		pubOrDetail = (PubOrDetail) activity;
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_square, container,
					false);
		}
		init(rootView);
		return rootView;
	}

	public interface PubOrDetail {
		public void publish();

		public void detail(int index);

		public void inform();
	}

	// ******************init******************

	private void init(View view) {
		findViews(view);
		initListView();
		setListener();
	}

	private void findViews(View view) {
		listView1 = (ListView) view.findViewById(R.id.listView1);
		backBu = (Button) view.findViewById(R.id.backBu);
		publishBu = (Button) view.findViewById(R.id.publishBu);
		informBu = (Button) view.findViewById(R.id.informBu);
	}

	private void initListView() {
		Context context = getActivity().getBaseContext();
		postAdapter = new MyAdapter(context, Post.TYPE);
		listView1.setAdapter(postAdapter);
	}

	private void setListener() {
		backBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		publishBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pubOrDetail.publish();
			}
		});

		informBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pubOrDetail.inform();
			}
		});

		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				pubOrDetail.detail(position);
			}
		});
	}
}
