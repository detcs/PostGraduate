package com.data.util;

public class ComputeURL {
	// private static final String TAG = "ComputeURL";
	// ************IMAGE************
	private static final int HEAD_WIDTH = 100;
	private static final int HEAD_HEIGHT = 100;
	private static final int SMALL_HEAD_HEIGHT = 50;
	private static final int SMALL_HEAD_WIDTH = 50;
	private static final String ImgUrlHead = GloableData.URL
			+ GloableData.IMAGE_PATH + "?id=";

	public static String getHeadImgURL(String id) {
		return ImgUrlHead + str(id) + "&w=" + HEAD_WIDTH + "&h=" + HEAD_HEIGHT;
	}

	public static String getSmallHeadImgURL(String id) {
		return ImgUrlHead + str(id) + "&w=" + SMALL_HEAD_WIDTH + "&h="
				+ SMALL_HEAD_HEIGHT;
	}

	// ***************************************************************
	public static String getPostDetailURL(String postId) {
		String re = GloableData.URL + GloableData.POST_DETAIL_PATH
				+ GloableData.getParam() + "&postid=" + str(postId);
		return re;
	}

	public static String getPublishPostURL(String title, String content) {
		String re = GloableData.URL + GloableData.PUBLISH_POST_PATH
				+ GloableData.getParam() + "&title=" + str(title) + "&content="
				+ str(content);
		return re;
	}

	public static String getCommentURL(String postid, String content,
			String replyid) {
		String re = GloableData.URL + GloableData.UPLOAD_COMMENT_PATH
				+ GloableData.getParam() + "&postid=" + str(postid)
				+ "&content=" + str(content) + "&replyid=" + str(replyid);
		return re;
	}

	public static String getDownloadURL(String id, String email,
			String resourceId, String isShared) {
		String reStr = GloableData.URL + GloableData.DOWNLOAD_PATH
				+ GloableData.getParam() + "&id=" + str(id) + "&email="
				+ str(email) + "&resid=" + str(resourceId) + "&isShared="
				+ str(isShared);
		return reStr;
	}

	public static String getEssenseDetailURL(String id) {
		String reStr = GloableData.URL + GloableData.ESSENSEDETAIL_PATH
				+ GloableData.getParam() + "&id=" + str(id);
		return reStr;
	}

	// DataBuffer
	public static String getListURL(int page, int limit, int type, String id,
			String queryKey) {
		String reStr;
		switch (type) {
		case GloableData.TYPE_EXERCISE:
		case GloableData.TYPE_INFORMATION:
		case GloableData.TYPE_MATERIAL:
			reStr = getEssenseListURL(page, limit, type, queryKey);
			break;
		case GloableData.TYPE_POST:
			reStr = getPostListURL(page, limit);
			break;
		case GloableData.TYPE_COMMENT:
			reStr = getCommentListURL(page, limit, id);
			break;
		case GloableData.TYPE_INFORM:
			reStr = getInformListURL(page, limit);
			break;
		default:
			reStr = "";
			SysCall.error("list url is error");
			break;
		}
		return reStr;
	}

	public static String getEssenseListURL(int page, int limit, int type,
			String queryKey) {
		String reStr = GloableData.URL + GloableData.ESSENSE_PATH
				+ GloableData.getParam() + "&page=" + page + "&limit=" + limit
				+ "&type=" + type + "&key=" + str(queryKey);
		return reStr;
	}

	public static String getPostListURL(int page, int limit) {
		String reStr = GloableData.URL + GloableData.POST_LIST_PATH
				+ GloableData.getParam() + "&page=" + page + "&limit=" + limit
				+ "&type=" + GloableData.TYPE_POST;
		return reStr;
	}

	public static String getCommentListURL(int page, int limit, String id) {
		String reStr = GloableData.URL + GloableData.COMMENT_PATH
				+ GloableData.getParam() + "&page=" + page + "&limit=" + limit
				+ "&postid=" + str(id);
		return reStr;
	}

	public static String getInformListURL(int page, int limit) {
		String reStr = GloableData.URL + GloableData.INFORM_PATH
				+ GloableData.getParam() + "&page=" + page + "&limit=" + limit;
		return reStr;
	}

	private static String str(String in) {
		if (null == in.trim()) {
			return "";
		} else {
			return in;
		}
	}
}
