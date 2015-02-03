package com.data.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.data.model.EssenseDetail;
import com.data.model.Post;

public class NetCall {
	// private static final String TAG = "NetCall";

	private static RequestQueue requestQueue = GloableData.requestQueue;

	public static void download(String id, String email, String resourceId,
			String isShared, final DownloadSource ds) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, ComputeURL.getDownloadURL(id, email,
						resourceId, isShared), null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {
							int errorCode = response.getInt("errorCode");
							if (0 == errorCode) {
								JSONObject data = (JSONObject) response
										.get("data");
								int code_ = data.getInt("code_");
								if (1 == code_) {
									// success
									ds.downloadSuccess();
									return;
								}
							}
							ds.downloadFail();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						ds.downloadFail();
					}
				});
		requestQueue.add(jsonObjectRequest);
	}

	public static void getEssenseDetail(String postId,
			final PullEssenseDetail pd) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, ComputeURL.getEssenseDetailURL(postId),
				null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {
							int errorCode = response.getInt("errorCode");
							if (errorCode != 0) {
								return;
							} else {
								// success
								pd.pullEDSuccess(Json_util
										.getEssenseDetail(response));
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Log.i("error", "wsy");
					}
				});
		requestQueue.add(jsonObjectRequest);
	}

	public static void getPost(String postId, final PullViewGenerator up) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, ComputeURL.getPostDetailURL(postId), null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// json trans
						Post vg = Json_util.transToOnePost(response);
						up.pullVGSuccess(vg);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						up.pullVGFail();
					}
				});
		requestQueue.add(jsonObjectRequest);
	}

	public static void post(String title, String content,
			final UploadData callback) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET,
				ComputeURL.getPublishPostURL(title, content), null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						String msg = Json_util.getUploadReturnMsg(response);
						if (msg.equals(GloableData.UPLOAD_SUCCESS_MSG)) {
							callback.updateSuccess();
						} else {
							callback.updatetFail();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						callback.updatetFail();
					}
				});
		requestQueue.add(jsonObjectRequest);
	}

	public static void comment(String postId, String content, String userId,
			final UploadData callback) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, ComputeURL.getCommentURL(postId, content,
						userId), null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						String msg = Json_util.getUploadReturnMsg(response);
						if (msg.equals(GloableData.UPLOAD_SUCCESS_MSG)) {
							callback.updateSuccess();
						} else {
							callback.updatetFail();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						callback.updatetFail();
					}
				});
		requestQueue.add(jsonObjectRequest);
	}

	public interface PullEssenseDetail {
		public void pullEDSuccess(EssenseDetail ed);

		public void pullEDFail();
	}

	public interface PullViewGenerator {
		// request one post detail
		public void pullVGSuccess(Post vg);

		public void pullVGFail();
	}

	public interface UploadData {
		public void updateSuccess();

		public void updatetFail();
	}

	public interface DownloadSource {
		public void downloadSuccess();

		public void downloadFail();
	}
}
