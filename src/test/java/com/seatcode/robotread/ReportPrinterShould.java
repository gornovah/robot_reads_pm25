package com.seatcode.robotread;

import com.google.maps.model.LatLng;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReportPrinterShould {

    @Test
    public void print_readings_with_timestamp_location_and_level() {
        Console console = mock(Console.class);
        ReportFormatter reportFormatter = new ReportFormatter();
        ReportPrinter reportPrinter = new ReportPrinter(console, reportFormatter);
        LatLng position = new LatLng(51.23241, -0.1223);
        String measure = "USG";
        long instant = 1528106219;
        Record record = new Record(null, measure, position, instant, "robot");

        reportPrinter.report(record);

        verify(console).print("{\"timestamp\":1528106219,\"location\":{\"lat\":51.23241,\"lng\":-0.1223},\"level\":\"USG\",\"source\":\"robot\"}");
    }
}