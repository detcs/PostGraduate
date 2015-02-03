package com.data.model;

public class EssenseDetail {
	public static final int HASRESOURCE = 1;
	public static final int HASDOWNLOADED = 1;
	public static final int NEEDSHARE = 1;

	private String id;
	private String url;
	private int hasDownload;// 1 表示有
	private String resourceId;
	private int isDownloaded;// 1表示已经下载过
	private int needShare;// 1表示需要分享

	public EssenseDetail(String id, String url, int hasDownload,
			String resourceId, int isDownloaded, int needShare) {
		this.id = id;
		this.url = url;
		this.hasDownload = hasDownload;
		this.resourceId = resourceId;
		this.isDownloaded = isDownloaded;
		this.needShare = needShare;
	}

	public String toString() {
		return id + " " + url + " " + hasDownload + " " + resourceId + " "
				+ isDownloaded + " " + needShare;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public int getHasDownload() {
		return hasDownload;
	}

	public String getResourceId() {
		return resourceId;
	}

	public int getIsDownloaded() {
		return isDownloaded;
	}

	public int getNeedShare() {
		return needShare;
	}

}
