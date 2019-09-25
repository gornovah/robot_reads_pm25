package com.seatcode.robotread.domain.services;

import com.seatcode.robotread.actions.ReadMeasure;

import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MeasureScheduler {
    private static final int SECONDS_TO_TRAVEL_100_METERS_AT_3MS = 33;
    private static final int INITIAL_DELAY = 0;
    private static final int MINUTES_TO_REPORT = 15;
    private final ReadMeasure readMeasure;
    private final ScheduledExecutorService scheduledExecutorService;

    public MeasureScheduler(ReadMeasure readMeasure, ScheduledExecutorService scheduledExecutorService) {

        this.readMeasure = readMeasure;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void scheduledRead() {
        Runnable runnableReadPm25Level = () -> readMeasure.readPm25Level();
        scheduledExecutorService.scheduleAtFixedRate(runnableReadPm25Level,
                INITIAL_DELAY,
                SECONDS_TO_TRAVEL_100_METERS_AT_3MS, 
                SECONDS);

    }

    public void scheduleReport() {
        Runnable runnableReportMeasure = () -> readMeasure.reportMeasure();
        scheduledExecutorService.scheduleAtFixedRate(runnableReportMeasure,
                INITIAL_DELAY,
                MINUTES_TO_REPORT,
                MINUTES);
    }
}
