package com.pages.funsquare.reserve;

import com.app.ydd.R;
import com.view.util.SysCall;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ReservedActivity extends Activity {
	private LinearLayout buttonContainer;
	private Button backBu;
	private ProgressBar progressBar1;
	private TextView textView3;
	private SharedPreferences preferences;
	private Editor editor;
	private final String TIMES = "click_time";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserved);
		init(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reserved, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// *************init*************
	private void init(boolean isReserved) {
		initShared();
		findViews();
		isNeedReserve(isReserved);
		initData();
		setListener();
	}

	private void initShared() {
		preferences = getSharedPreferences("click_time", MODE_PRIVATE);
		editor = preferences.edit();
	}

	private void findViews() {
		backBu = (Button) findViewById(R.id.backBu);
		buttonContainer = (LinearLayout) findViewById(R.id.buttonContainer);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		textView3 = (TextView) findViewById(R.id.textView3);
	}

	private void isNeedReserve(boolean isNeed) {
		if (isNeed) {
			Button reserveBu = new Button(ReservedActivity.this);
			reserveBu.setText("一键备份");
			buttonContainer.addView(reserveBu);
			reserveBu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					editor.putInt(TIMES, preferences.getInt(TIMES, 0) + 1)
							.commit();
				}
			});
		}
	}

	private void initData() {
		int times = preferences.getInt(TIMES, 0);
		textView3.setText("您已经点击备份键" + times + "次。");
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
}
