package com.data.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.data.model.Material;
import com.view.util.ViewGenerator;

public class Json_util {
	// private static ObjectMapper mapper = new ObjectMapper();

	public static List<ViewGenerator> transToMaterial(JSONObject json,
			int type, int number) {
		List<ViewGenerator> reList = new ArrayList<ViewGenerator>();
		try {
			JSONObject data = (JSONObject) json.get("data");
			JSONArray array = (JSONArray) data.get("essence_");
			JSONObject item;
			String author = "";
			String title = "";
			String time = "";
			for (int i = 0; i < array.length(); i++) {
				item = array.getJSONObject(i);
				title = item.getString("title_");
				time = item.getString("time_");
				reList.add(new Material(title, author, time));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reList;
	}
}
