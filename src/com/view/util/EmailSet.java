package com.view.util;

import com.data.util.GloableData;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

public class EmailSet {
	public static void setEmail(Context context, final EmainSetInterface es) {
		final EditText inputServer = new EditText(context);
		inputServer.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		inputServer.setFocusable(true);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Input your email address:")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(inputServer).setNegativeButton("Cancel", null);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				String emailAddress = inputServer.getText().toString();
				if (GloableData.setEmail(emailAddress)) {
					es.setSuccess(emailAddress);
					return;
				}
				es.setFail();
			}
		});
		builder.show();
	}

	public interface EmainSetInterface {
		public void setSuccess(String email);

		public void setFail();
	}
}
