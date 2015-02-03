package com.data.util;

import java.util.List;

import org.json.JSONObject;

import com.data.model.Post;

public class PostUtil implements NetUtil<Post> {

	@Override
	public String getURL(int page, int limit) {
		// TODO Auto-generated method stub
		return ComputeURL.getPostListURL(page, limit);
	}

	@Override
	public List<Post> parseToVG(JSONObject response) throws Exception {
		// TODO Auto-generated method stub
		Json_util.checkErrorCode(response);
		return Json_util.transToPost(response);
	}

}
