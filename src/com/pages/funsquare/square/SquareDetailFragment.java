package com.pages.funsquare.square;

import com.app.ydd.R;
import com.data.model.Post;
import com.data.util.NetCall;
import com.data.util.SysCall;
import com.data.util.NetCall.UploadData;
import com.view.util.AdapterFresh;
import com.view.util.CommentAdapter;
import com.view.util.CommentAdapter.Reply;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SquareDetailFragment extends Fragment implements Reply, UploadData {
	private static final String TAG = "SquareDetailFragment";
	// private Context context;
	private View rootView;
	// head bar
	private Button backBu;
	private Button shareBu;
	// show zone
	private ListView listView1;
	// comment bar
	private EditText editText;
	private Button commentBu;

	private CommentAdapter commentAdapter;
	private Post vg;
	// comment
	private String userId;
	private String replyHead;

	public SquareDetailFragment(Post vg) {
		// TODO Auto-generated constructor stub
		this.vg = vg;
	}

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
	
	@Override
	public void onPause(){
		super.onPause();
		commentAdapter.fresh();
	}

	// ***************init***************
	private void init(View view) {
		findViews(view);
		initListView();
		setListener();
	}

	private void findViews(View view) {
		backBu = (Button) view.findViewById(R.id.backBu);
		shareBu = (Button) view.findViewById(R.id.shareBu);
		listView1 = (ListView) view.findViewById(R.id.listView1);
		commentBu = (Button) view.findViewById(R.id.commentBu);
		editText = (EditText) view.findViewById(R.id.editText);
	}

	private void initListView() {
		commentAdapter = new CommentAdapter(getActivity(), vg.getId(), this, vg);
		listView1.setAdapter(commentAdapter);
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
				String content = editText.getText().toString();
				if (null == content) {
					Log.i(TAG, content + "");
				}
				if (!content.startsWith(replyHead + "")) {
					userId = "";
				}
				NetCall.comment(vg.getId(), content, userId,
						SquareDetailFragment.this);
			}
		});
	}

	@Override
	public void reply(String userId, int position) {
		// TODO Auto-generated method stub
		this.userId = userId;
		if (position != 0) {
			replyHead = "回复" + position + "楼:";
			editText.setText(replyHead);
			SysCall.bumpSoftInput(editText, getActivity());
		}
	}

	// 这两个方法仅仅是为了通知用户的，真正的通知本地数据刷新不在这里做
	@Override
	public void updateSuccess() {
		// TODO Auto-generated method stub
		SysCall.hint(getActivity(), "评论成功");
		((AdapterFresh) commentAdapter).fresh();
		editText.setText("");
	}

	@Override
	public void updatetFail() {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "评论失败", Toast.LENGTH_SHORT).show();
	}
}
