package com.data.model;

public class Comment {
	private String id;
	private String nickName;
	private String content;
	private String headimg;
	private String time;
	private String userId;

	public Comment(String id, String nickName, String headimg, String time,
			String content, String userId) {
		this.id = id + "";
		this.nickName = nickName + "";
		this.headimg = headimg + "";
		this.time = time + "";
		this.content = content + "";
		this.userId = userId;
	}

	public String getAuthor() {
		// TODO Auto-generated method stub
		return nickName;
	}

	public String getTime() {
		// TODO Auto-generated method stub
		return time;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getImgNo() {
		// TODO Auto-generated method stub
		return headimg;
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	public String getUserId() {
		return userId;
	}

}
