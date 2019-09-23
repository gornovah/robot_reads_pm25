package com.seatcode.robotread;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;

public class ReportFormatter {
    public String execute(Record record) {
        ObjectWriter ow = new ObjectMapper().writer();
        try {
            return ow.writeValueAsString(record);
        } catch (IOException e) {
            throw new RobotException("Report formatter has an error" + e.getMessage());
        }
    }
}
