package com.data.util;

import java.util.List;

import org.json.JSONObject;

import com.data.model.Inform;

public class InformUtil implements NetUtil<Inform> {

	@Override
	public String getURL(int page, int limit) {
		// TODO Auto-generated method stub
		return ComputeURL.getInformListURL(page, limit);
	}

	@Override
	public List<Inform> parseToVG(JSONObject response) throws Exception {
		// TODO Auto-generated method stub
		Json_util.checkErrorCode(response);
		return Json_util.transToInform(response);
	}

}
