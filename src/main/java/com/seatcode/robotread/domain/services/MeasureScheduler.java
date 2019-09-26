package com.seatcode.robotread.domain.services;

import com.seatcode.robotread.actions.RobotSystem;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MeasureScheduler {
    private static final int SECONDS_TO_TRAVEL_100_METERS_AT_3MS = 33;
    private static final int INITIAL_DELAY = 0;
    private static final int MINUTES_TO_REPORT = 15;
    private final RobotSystem robotSystem;
    private final ScheduledExecutorService scheduledExecutorService;
    private final AtomicInteger count = new AtomicInteger(0);


    public MeasureScheduler(RobotSystem robotSystem, ScheduledExecutorService scheduledExecutorService) {

        this.robotSystem = robotSystem;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void scheduledRead() {
        Runnable runnableReadPm25Level = () -> {
            robotSystem.readPm25Level();
            count.getAndIncrement();
        };
        scheduledExecutorService.scheduleAtFixedRate(runnableReadPm25Level,
                INITIAL_DELAY,
                SECONDS_TO_TRAVEL_100_METERS_AT_3MS,
                SECONDS);
    }

    public void scheduleReport() {
        Runnable runnableReportMeasure = () -> robotSystem.reportMeasure();
        scheduledExecutorService.scheduleAtFixedRate(runnableReportMeasure,
                INITIAL_DELAY,
                MINUTES_TO_REPORT,
                MINUTES);
    }

    public void stopScheduledExecutorService(int limitOfInvocations) throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            if (count.get() >= limitOfInvocations) {
                scheduledExecutorService.shutdown();
                break;
            }
        }
    }
}
