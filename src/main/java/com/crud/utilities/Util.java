package com.crud.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Util {

    private Util() {}

    public static JSONObject getdata(Map<String, Object> data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject.getJSONObject("data");
    }
}
