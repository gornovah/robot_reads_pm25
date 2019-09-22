package com.seatcode.robotread;

import com.seatcode.robotread.ReportPrinter;
import com.seatcode.robotread.Robot;
import org.junit.jupiter.api.Test;


import java.sql.Timestamp;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RobotReadFeature {

    @Test
    public void return_the_report_of_new_readings() {

        ReportPrinter reportPrinter = mock(ReportPrinter.class);
        Robot robot = mock(Robot.class);
        Position position = new Position("51.23241", "-0.1223");
        Clock timestamp = mock(Clock.class);
        given(timestamp.timestampAsString()).willReturn("1528106219");

        robot.readLevel(position, timestamp);
        robot.reportMeasure();

        verify(reportPrinter).report("{ \n" +
                "   timestamp:1528106219,\n" +
                "   location:{ \n" +
                "      lat:51.23241,\n" +
                "      lng:-0.1223\n" +
                "   },\n" +
                "   level:\"USG\",\n" +
                "   source:\"robot\"\n" +
                "}");
    }
}
