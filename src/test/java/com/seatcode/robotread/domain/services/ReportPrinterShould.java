package com.seatcode.robotread.domain.services;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.api.ReportFormatter;
import com.seatcode.robotread.domain.services.ReportPrinter;
import com.seatcode.robotread.domain.model.Average;
import com.seatcode.robotread.api.Console;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReportPrinterShould {

    @Test
    public void print_readings_with_timestamp_location_and_level() throws ParseException {
        Console console = mock(Console.class);
        ReportFormatter reportFormatter = new ReportFormatter();
        ReportPrinter reportPrinter = new ReportPrinter(console, reportFormatter);
        LatLng position = new LatLng(51.23241, -0.1223);
        int measure = 140;
        long instant = 1528106219;
        Average average = new Average(measure, position, instant, "robot");

        reportPrinter.report(average);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(console).print(argumentCaptor.capture());
        JSONParser parser = new JSONParser();
        JSONObject actualJson = (JSONObject) parser.parse(argumentCaptor.getValue());
        JSONObject expectedJson = (JSONObject) parser.parse("{\"timestamp\":1528106219,\"location\":{\"lat\":51.23241,\"lng\":-0.1223},\"level\":\"USG\",\"source\":\"robot\"}");

        assertThat(actualJson).isEqualTo(expectedJson);

    }
}