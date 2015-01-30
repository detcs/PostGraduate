package com.pages.funsquare.square;


import com.app.ydd.R;
import com.view.util.SysCall;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SquareDetailFragment extends Fragment {
	private View rootView;
	private Button backBu;
	private Button shareBu;
	private Button commentBu;
	private ListView listView1;
	private EditText editText;
	private BaseAdapter postAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_square_detail,
					container, false);
		}
		init(rootView);
		return rootView;
	}

	// ***************init***************
	private void init(View view) {
		findViews(view);
		setListener();
		initListView();
	}

	private void findViews(View view) {
		backBu = (Button) view.findViewById(R.id.backBu);
		shareBu = (Button) view.findViewById(R.id.shareBu);
		commentBu = (Button) view.findViewById(R.id.commentBu);
		listView1 = (ListView) view.findViewById(R.id.listView1);
		editText = (EditText) view.findViewById(R.id.editText);
	}

	private void initListView() {
		postAdapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView textView = new TextView(getActivity());
				textView.setText("textview" + position);
				return textView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 30;
			}
		};
		listView1.setAdapter(postAdapter);
	}

	private void setListener() {
		backBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SysCall.clickBack();
			}
		});
		shareBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		commentBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position != 0) {
					editText.setText("回复" + position + "楼：");
				}
			}
		});
	}
}
