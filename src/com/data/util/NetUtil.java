package com.data.util;

import java.util.List;

import org.json.JSONObject;

public interface NetUtil<D> {
	public String getURL(int page, int limit);

	public List<D> parseToVG(JSONObject response) throws Exception;
}
