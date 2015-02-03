package com.data.model;

import com.data.util.GloableData;

public class Inform {
	private String title;
	private String postId;
	private String comment;
	private int tag;

	public Inform(String title, String postId, String comment, int tag) {
		this.title = title;
		this.postId = postId;
		this.comment = comment;
		this.tag = tag;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return postId;
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return comment;
	}

	public String getIsNewTag() {
		// TODO Auto-generated method stub
		String tagStr = tag == GloableData.ISNEW ? "未读" : "已读";
		return tagStr;
	}

}
