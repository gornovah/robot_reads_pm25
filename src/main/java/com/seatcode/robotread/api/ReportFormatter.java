package com.seatcode.robotread.api;

import com.seatcode.robotread.domain.model.Average;
import org.json.simple.JSONObject;
/**
 * Format the output message to Json format
 * @author despinosa
 *
 */
public class ReportFormatter {
    public String parseToJson(Average average, String value) {
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
