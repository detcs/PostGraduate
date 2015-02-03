package com.data.model;

/*
 * 用于显示精华的资料列表：用于显示其中一项
 * 
 * */
public class Essense {
	private String author;
	private String title;
	private String time;
	private String id;

	public Essense() {
		// TODO Auto-generated constructor stub
		author = "wsy";
		title = "material";
		time = "2015-1-29";
	}

	public Essense(String title, String author, String time, String id) {
		this.title = title;
		this.time = time;
		this.author = author;
		this.id = id;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public String getAuthor() {
		// TODO Auto-generated method stub
		return author;
	}

	public String getTime() {
		// TODO Auto-generated method stub
		return time;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
