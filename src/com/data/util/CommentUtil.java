package com.data.util;

import java.util.List;

import org.json.JSONObject;

import com.data.model.Comment;

public class CommentUtil implements NetUtil<Comment> {
	private String postId;

	public CommentUtil(String postId) {
		this.postId = postId;
	}

	@Override
	public String getURL(int page, int limit) {
		// TODO Auto-generated method stub
		return ComputeURL.getCommentListURL(page, limit, postId);
	}

	@Override
	public List<Comment> parseToVG(JSONObject response) throws Exception {
		// TODO Auto-generated method stub
		Json_util.checkErrorCode(response);
		return Json_util.transToComment(response);
	}
}
