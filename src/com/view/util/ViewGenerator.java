package com.view.util;

import android.view.View;

public interface ViewGenerator {
	// 用于在列表中显示
	public View getBriefView();

	// 用于在单项中显示详细信息：资料，消息，真题；评论没法用，因为详细信息中也是显示列表
	public View getDetailView();

	// 释放在创建简要信息时索取的资源
	public void releaseBrief();

	// 释放在创建详情浏览时索取的资源
	public void releaseDetail();

	// 释放自身从网络获得的资源
	public void release();
}
