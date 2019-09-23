package com.seatcode.robotread;

import org.json.simple.JSONObject;

public class ReportFormatter {
    public String execute(Average average, String value) {
        JSONObject resultAverageJson = new JSONObject();
        resultAverageJson.put("timestamp", average.getTimestamp());
        JSONObject locationsJson = new JSONObject();
        locationsJson.put("lat", average.getLocation().lat);
        locationsJson.put("lng", average.getLocation().lng);
        resultAverageJson.put("location", locationsJson);
        resultAverageJson.put("level", value);
        resultAverageJson.put("source", average.getSource());
        return resultAverageJson.toJSONString();
    }
}
