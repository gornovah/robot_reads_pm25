package com.seatcode.robotread.domain.services;

import com.seatcode.robotread.actions.Robot;

import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MeasureScheduler {
    private final int SECONDS_TO_TRAVEL_100_METERS_AT_3MS = 33;
    private final int INITIAL_DELAY = 0;
    private final int MINUTES_TO_REPORT = 15;
    private Robot robot;
    private ScheduledExecutorService scheduledExecutorService;

    public MeasureScheduler(Robot robot, ScheduledExecutorService scheduledExecutorService) {

        this.robot = robot;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void scheduledRead() {
        Runnable runnableReadPm25Level = () -> robot.readPm25Level();
        scheduledExecutorService.scheduleAtFixedRate(runnableReadPm25Level,
                INITIAL_DELAY,
                SECONDS_TO_TRAVEL_100_METERS_AT_3MS, 
                SECONDS);

    }

    public void scheduleReport() {
        Runnable runnableReportMeasure = () -> robot.reportMeasure();
        scheduledExecutorService.scheduleAtFixedRate(runnableReportMeasure,
                INITIAL_DELAY,
                MINUTES_TO_REPORT,
                MINUTES);
    }
}
