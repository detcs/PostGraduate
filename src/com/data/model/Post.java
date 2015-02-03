package com.data.model;

/*
 * 用于显示广场帖子列表：用于显示其中一项
 * 
 * */
public class Post {
	private String title;
	private String nickName;
	private String time;
	private String headimg;
	private String id;
	private String content;
	private int comments;

	public Post(String title, String nickName, String time, String id,
			String headimg, String content, int comments) {
		this.title = title;
		this.nickName = nickName;
		this.time = time;
		this.headimg = headimg;
		this.id = id;
		this.content = content;
		this.comments = comments;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
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

	public int getComments() {
		// TODO Auto-generated method stub
		return comments;
	}
}
