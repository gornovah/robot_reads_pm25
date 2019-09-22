package com.seatcode.robotread;

import com.google.maps.model.LatLng;

import java.time.Instant;
import java.util.List;

public class Robot {

    private LatLng position;
    private ReadLevel readLevel;
    private MeasureRepository measureRepository;

    public Robot(ReadLevel readLevel, MeasureRepository measureRepository) {
        this.readLevel = readLevel;
        this.measureRepository = measureRepository;
    }

    public void start(PolylineRoute polylineRoute) {
        move(polylineRoute);
    }

    public void readPm25Level() {
        String measure = readLevel.execute();
        LatLng position = position();
        long instant = Instant.now().toEpochMilli();
        Record record = new Record(measure, position, instant);
        measureRepository.save(record);
    }

    public void reportMeasure() {
        throw new UnsupportedOperationException();
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
