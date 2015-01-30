package com.view.util;

public interface ViewGenerator {
	public static final String DEFAULT_TITLE = "MyTitle";
	public static final String DEFAULT_AUTHOR = "wsy";
	public static final String DEFAULT_TIME = "0:00";

	public String getTitle();

	public String getAuthor();

	public String getTime();
}
