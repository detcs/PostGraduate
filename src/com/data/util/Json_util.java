package com.data.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.data.model.Comment;
import com.data.model.Essense;
import com.data.model.EssenseDetail;
import com.data.model.Inform;
import com.data.model.Post;

public class Json_util {

	public static void checkErrorCode(JSONObject json) throws Exception {
		int errorcode = -1;
		try {
			errorcode = json.getInt("errorCode");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (errorcode != 0) {
			throw new Exception("error code is not 0");
		}
	}

	// upload comment
	public static String getUploadReturnMsg(JSONObject json) {
		String reStr;
		try {
			JSONObject data = (JSONObject) json.get("data");
			reStr = data.getString("msg_");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reStr = "error";
		}
		return reStr;
	}

	public static EssenseDetail getEssenseDetail(JSONObject json) {
		EssenseDetail ed = null;
		try {
			JSONObject data = (JSONObject) json.get("data");
			String id = data.getString("id_");
			String url = data.getString("url_");
			int hasDownload = data.getInt("hasDownload_");
			String resourceId = "";
			if (EssenseDetail.HASRESOURCE == hasDownload) {
				resourceId = data.getString("resid_");
			}
			int isDownloaded = data.getInt("isDownloaded_");
			int needShare = data.getInt("needShare_");
			ed = new EssenseDetail(id, url, hasDownload, resourceId,
					isDownloaded, needShare);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ed;
	}

	public static String getEssenseContentURL(JSONObject json) {
		String url_ = "http://www.baidu.com";
		try {
			JSONObject data = (JSONObject) json.get("data");
			url_ = data.getString("url_");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url_;
	}

	public static Post transToOnePost(JSONObject json) {
		Post vg = null;
		try {
			JSONObject data = (JSONObject) json.get("data");

			String title = data.getString("title_");
			String time = data.getString("time_");
			String id = data.getString("id_");
			String author = data.getString("nickname_");
			String headimg = data.getString("headimg_");
			String content = data.getString("content_");
			int comments = data.getInt("commentCount_");

			vg = new Post(title, author, time, id, headimg, content, comments);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vg;
	}

	public static List<Inform> transToInform(JSONObject json) {
		List<Inform> reList = new ArrayList<Inform>();
		try {
			JSONObject data = (JSONObject) json.get("data");
			JSONArray array = (JSONArray) data.get("comments_");
			JSONObject item;
			Inform vg = null;
			String title;
			String postId;
			String comment;
			int tag;
			for (int i = 0; i < array.length(); i++) {
				item = array.getJSONObject(i);
				title = item.getString("title_");
				postId = item.getString("pid_");
				comment = item.getString("content_");
				tag = item.getInt("isNew_");
				vg = new Inform(title, postId, comment, tag);
				reList.add(vg);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reList;
	}

	public static List<Comment> transToComment(JSONObject json) {
		List<Comment> reList = new ArrayList<Comment>();
		try {
			JSONObject data = (JSONObject) json.get("data");
			JSONArray array = (JSONArray) data.get("comments_");
			JSONObject item;
			Comment vg = null;
			String id;
			String nickName;
			String content;
			String headimg;
			String time;
			String userId;
			for (int i = 0; i < array.length(); i++) {
				item = array.getJSONObject(i);
				id = item.getString("id_");
				nickName = item.getString("nickname_");
				content = item.getString("content_");
				headimg = item.getString("headimg_");
				time = item.getString("time_");
				userId = item.getString("userid_");
				// Comment(String id, String nickName, String headimg, String
				// time,String content)
				vg = new Comment(id, nickName, headimg, time, content, userId);
				reList.add(vg);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reList;
	}

	public static List<Post> transToPost(JSONObject json) {
		List<Post> reList = new ArrayList<Post>();
		try {
			JSONObject data = (JSONObject) json.get("data");
			JSONArray array = (JSONArray) data.get("list_");
			JSONObject item;
			Post vg = null;
			String author = "";
			String title = "";
			String time = "";
			String id = "";
			String headimg = "";
			String content = "";
			int comments = 0;
			for (int i = 0; i < array.length(); i++) {
				item = array.getJSONObject(i);
				title = item.getString("title_");
				time = item.getString("time_");
				id = item.getString("id_");
				author = item.getString("nickname_");
				headimg = item.getString("headimg_");
				content = item.getString("content_");
				comments = item.getInt("commentCount_");
				vg = new Post(title, author, time, id, headimg, content,
						comments);
				reList.add(vg);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reList;
	}

	public static List<Essense> transToEssense(JSONObject json) {
		List<Essense> reList = new ArrayList<Essense>();
		try {
			JSONObject data = (JSONObject) json.get("data");
			JSONArray array = (JSONArray) data.get("essence_");
			JSONObject item;
			Essense vg = null;
			String author = "";
			String title = "";
			String time = "";
			String id = "";
			for (int i = 0; i < array.length(); i++) {
				item = array.getJSONObject(i);
				title = item.getString("title_");
				time = item.getString("time_");
				id = item.getString("id_");
				vg = new Essense(title, author, time, id);
				reList.add(vg);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reList;
	}
}
