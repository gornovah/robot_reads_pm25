package com.seatcode.robotread;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.repository.MeasureRepository;

import java.time.Instant;
import java.util.List;

public class Robot {

    private LatLng position;
    private ReadLevel readLevel;
    private MeasureRepository measureRepository;
    private ReportPrinter reportPrinter;

    public Robot(ReadLevel readLevel, MeasureRepository measureRepository, ReportPrinter reportPrinter) {
        this.readLevel = readLevel;
        this.measureRepository = measureRepository;
        this.reportPrinter = reportPrinter;
    }

    public void start(PolylineRoute polylineRoute) {
        move(polylineRoute);
    }

    public void readPm25Level() {
        int measure = readLevel.execute();
        LatLng position = position();
        long instant = Instant.now().toEpochMilli();
        Record record = new Record(null, measure, position, instant, "robot");
        measureRepository.save(record);
    }

    public void reportMeasure() {
        Average average = measureRepository.load();
        reportPrinter.report(average);
    }

    public LatLng position() {
        return position;
    }

    private void move(PolylineRoute polylineRoute) {
        List<LatLng> decodedPolyline = polylineRoute.decode();
        for (LatLng latLng : decodedPolyline) {
            position = latLng;
        }
    }
}
