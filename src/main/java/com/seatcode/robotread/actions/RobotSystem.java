package com.seatcode.robotread.actions;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.api.decoder.PolylineDecoder;
import com.seatcode.robotread.domain.services.RouteService;
import com.seatcode.robotread.domain.model.Average;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.domain.services.ReportPrinter;
import com.seatcode.robotread.infrastructure.ReadLevel;
import com.seatcode.robotread.repository.MeasureRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RobotSystem {

    private LatLng position;
    private final ReadLevel readLevel;
    private final MeasureRepository measureRepository;
    private final ReportPrinter reportPrinter;
    public RouteService routeService;
    private static AtomicLong at = new AtomicLong(0L);
    private List<LatLng> decodedPolyline;
    private Instant startTime;
    private double robotSpeed = 3;


    public RobotSystem(ReadLevel readLevel, MeasureRepository measureRepository, ReportPrinter reportPrinter, RouteService routeService) {
        this.readLevel = readLevel;
        this.measureRepository = measureRepository;
        this.reportPrinter = reportPrinter;
        this.routeService = routeService;
    }

    public void start(PolylineDecoder polylineDecoder) {
        decodedPolyline = polylineDecoder.decode();
        startTime = Instant.now();
    }

    public void readPm25Level() {
        int measure = readLevel.execute();
        Instant now = Instant.now();
        Duration time = Duration.between(startTime, now);
        Double distance = time.getSeconds() * robotSpeed;
        position = routeService.withinRoute(decodedPolyline, distance);
        Record record = new Record(getNextCountValue(), measure, position, now.toEpochMilli(), "robot");
        measureRepository.save(record);
    }

    public void reportMeasure() {
        Average average = measureRepository.load();
        reportPrinter.report(average);
    }

    public LatLng position() {
        return position;
    }

    private long getNextCountValue() {
        return at.incrementAndGet();
    }


}
