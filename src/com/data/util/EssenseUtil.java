package com.data.util;

import java.util.List;

import org.json.JSONObject;

import com.data.model.Essense;

public class EssenseUtil implements NetUtil<Essense> {
	private int type;
	private String queryKey;

	public EssenseUtil(int type, String queryKey) {
		this.type = type;
		this.queryKey = queryKey;
	}

	@Override
	public String getURL(int page, int limit) {
		// TODO Auto-generated method stub
		return ComputeURL.getEssenseListURL(page, limit, type, queryKey);
	}

	@Override
	public List<Essense> parseToVG(JSONObject response) throws Exception {
		// TODO Auto-generated method stub
		Json_util.checkErrorCode(response);
		return Json_util.transToEssense(response);
	}

}
