package com.pages.funsquare.essence;

import com.app.ydd.R;
import com.data.model.EssenseDetail;
import com.data.util.NetCall.DownloadSource;
import com.data.util.GloableData;
import com.data.util.NetCall;
import com.data.util.SysCall;
import com.view.util.EmailSet;
import com.view.util.EmailSet.EmainSetInterface;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;

public class EssenseDetailFragment extends Fragment implements DownloadSource,
		EmainSetInterface {
	// private static final String TAG = "EssenseDetailFragment";
	private View rootView;
	private EssenseDetail ed;

	private FrameLayout FrameLayout1;

	private Button backBu;
	private Button downBu;// 不一定有
	private WebView webView1;

	public EssenseDetailFragment(EssenseDetail ed) {
		this.ed = ed;
		// context = getActivity();
	}

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
		if (EssenseDetail.HASRESOURCE == ed.getHasDownload()) {
			View topView = (View) inflater.inflate(R.layout.square_detail_top,
					FrameLayout1, false);
			FrameLayout1.addView(topView);
			downBu = (Button) topView.findViewById(R.id.downBu);
			downBu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					download();
				}
			});
		}
		return rootView;
	}

	private void init(View view) {
		findViews(view);
		initWebView();
		setListener();
	}

	private void findViews(View view) {
		FrameLayout1 = (FrameLayout) view.findViewById(R.id.FrameLayout1);
		backBu = (Button) view.findViewById(R.id.backBu);
		webView1 = (WebView) view.findViewById(R.id.webView1);
	}

	private void initWebView() {
		// webView1.getSettings().setJavaScriptEnabled(true);
		webView1.loadUrl(ed.getUrl());
		webView1.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

	private void setListener() {
		backBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SysCall.clickBack();
			}
		});
	}

	private void download() {
		// 如果没有资源文件，则不会执行到这里
		if (EssenseDetail.NEEDSHARE == ed.getNeedShare()) {
			SysCall.hint(getActivity(), "share first");
			return;
		}
		if (!GloableData.hasSetEmail()) {
			// 检验邮箱是否已经设置
			EmailSet.setEmail(getActivity(), this);
		} else {
			NetCall.download(ed.getId(), "314784088@qq.com",
					ed.getResourceId(), "" + ed.getNeedShare(), this);
		}
	}

	@Override
	public void downloadSuccess() {
		// TODO Auto-generated method stub
		SysCall.hint(getActivity(), "download success");
	}

	@Override
	public void downloadFail() {
		// TODO Auto-generated method stub
		SysCall.hint(getActivity(), "download fail");
	}

	// EmainSet:EmainSetInterface
	@Override
	public void setSuccess(String email) {
		// TODO Auto-generated method stub
		NetCall.download(ed.getId(), "314784088@qq.com", ed.getResourceId(), ""
				+ ed.getNeedShare(), this);
	}

	// EmainSet:EmainSetInterface
	@Override
	public void setFail() {
		// TODO Auto-generated method stub
		SysCall.hint(getActivity(), "email set fail");
	}
}
