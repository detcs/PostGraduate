package com.data.model;

import com.view.util.ViewGenerator;

/*
 * 用于显示精华的资料列表：用于显示其中一项
 * 
 * */
public class Material implements ViewGenerator {
	public static final int TYPE = 0;
	private String author;
	private String title;
	private String time;

	public Material() {
		// TODO Auto-generated constructor stub
		author = "wsy";
		title = "material";
		time = "2015-1-29";
	}

	public Material(String title, String author, String time) {
		this.title = title;
		this.time = time;
		this.author = author;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return author;
	}

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return time;
	}

}
