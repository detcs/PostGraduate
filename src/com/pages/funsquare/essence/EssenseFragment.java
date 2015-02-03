package com.pages.funsquare.essence;

import com.app.ydd.R;
import com.data.util.GloableData;
import com.data.util.SysCall;
import com.view.util.EssenseAdapter;
import com.view.util.EssenseAdapter.ViewHolder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class EssenseFragment extends Fragment {
	// private static final String TAG = "EssenseFragment";
	private static final String TAB1 = "资讯";
	private static final String TAB2 = "视屏";
	private static final String TAB3 = "试题";
	private EssenseJump jump;
	private View rootView;
	private TabHost tabhost;
	private int queryType;
	private ListView listView1;
	private ListView listView2;
	private ListView listView3;
	private EssenseAdapter[] adapters;
	private SearchView searchView1;
	private TextView textView1;
	private boolean isInit = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_essense, container,
					false);
		}
		initVariable(rootView);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof EssenseJump)) {
			throw new IllegalStateException("error");
		}
		jump = (EssenseJump) activity;
	}

	@Override
	public void onPause() {
		super.onPause();
		for (int i = 0; i < 3; i++) {
			adapters[i].fresh();
		}
	}

	// *********init variable*********

	private void initVariable(View view) {
		queryType = GloableData.TYPE_MATERIAL;
		findViews(view);
		if (isInit) {
			initTabHost();
			initListViews();
		}
		isInit = false;
		setListener();
	}

	private void findViews(View view) {
		textView1 = (TextView) view.findViewById(R.id.textView1);
		tabhost = (TabHost) view.findViewById(R.id.tabhost);
		listView1 = (ListView) view.findViewById(R.id.listView1);
		listView2 = (ListView) view.findViewById(R.id.listView2);
		listView3 = (ListView) view.findViewById(R.id.listView3);
		searchView1 = (SearchView) view.findViewById(R.id.searchView1);
	}

	private void initListViews() {
		Context context = getActivity().getBaseContext();
		adapters = new EssenseAdapter[3];
		adapters[GloableData.TYPE_MATERIAL] = new EssenseAdapter(context,
				GloableData.TYPE_MATERIAL, "");
		adapters[GloableData.TYPE_INFORMATION] = new EssenseAdapter(context,
				GloableData.TYPE_INFORMATION, "");
		adapters[GloableData.TYPE_EXERCISE] = new EssenseAdapter(context,
				GloableData.TYPE_EXERCISE, "");
		listView1.setAdapter(adapters[GloableData.TYPE_MATERIAL]);
		listView2.setAdapter(adapters[GloableData.TYPE_INFORMATION]);
		listView3.setAdapter(adapters[GloableData.TYPE_EXERCISE]);
	}

	@SuppressLint("ResourceAsColor")
	private void initTabHost() {
		// Call setup() before adding tabs if loading TabHost using
		// findViewById(). However: You do not need to call setup() after
		// getTabHost() in TabActivity.
		tabhost.setup();
		tabhost.addTab(tabhost.newTabSpec(TAB1).setIndicator(TAB1)
				.setContent(R.id.tab1));
		tabhost.addTab(tabhost.newTabSpec(TAB2).setIndicator(TAB2)
				.setContent(R.id.tab2));
		tabhost.addTab(tabhost.newTabSpec(TAB3).setIndicator(TAB3)
				.setContent(R.id.tab3));
		for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
			View view = tabhost.getTabWidget().getChildAt(i);
			// view.setBackgroundColor(R.color.essense_tabhost_background);
			TextView textView = (TextView) view
					.findViewById(android.R.id.title);
			textView.setTextSize(20);
			textView.setBackgroundColor(R.color.essense_tabhost_background);
			textView.setTextColor(R.color.essense_tabhost_word);
			textView.setGravity(Gravity.CENTER);
			textView.getLayoutParams().height = LayoutParams.FILL_PARENT;
			textView.getLayoutParams().width = LayoutParams.FILL_PARENT;
			ImageView image = (ImageView) view.findViewById(android.R.id.icon);
			// image.setBackgroundColor(R.color.essense_tabhost_background);
			image.setBackgroundResource(R.drawable.default_head);// userful
		}
	}

	private void setListener() {
		textView1.setOnClickListener(new OnClickListener() {

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
				jump(view);
			}
		});
		listView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				jump(view);
			}
		});
		listView3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				jump(view);
			}
		});
		tabhost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				switch (tabId) {
				case TAB1:
					queryType = GloableData.TYPE_MATERIAL;
					break;
				case TAB2:
					queryType = GloableData.TYPE_INFORMATION;
					break;
				case TAB3:
					queryType = GloableData.TYPE_EXERCISE;
					break;
				default:
					SysCall.error("tab type is error");
					break;
				}
			}
		});
		searchView1.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				adapters[queryType].search(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				if (newText.trim().equals("")) {
					adapters[queryType].search("");
				}
				return false;
			}
		});
	}

	private void jump(View view) {
		ViewHolder holder = (ViewHolder) view.getTag();
		String id = holder.id;
		jump.detail(id);
	}
}
