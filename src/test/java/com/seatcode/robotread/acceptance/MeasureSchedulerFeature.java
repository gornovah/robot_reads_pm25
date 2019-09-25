package com.seatcode.robotread.acceptance;

import com.seatcode.robotread.domain.services.MeasureScheduler;
import com.seatcode.robotread.actions.Robot;
import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MeasureSchedulerFeature {

    private final long INITIAL_DELAY = 0L;
    private final long SECONDS_TO_TRAVEL_100_METERS_AT_3MS = 33L;
    private final long MINUTES_TO_REPORT = 15L;

    @Test
    public void read_every_100_meter() {

        Robot robot = mock(Robot.class);
        ScheduledExecutorService scheduledExecutorService = mock(ScheduledExecutorService.class);
        MeasureScheduler measureScheduler = new MeasureScheduler(robot, scheduledExecutorService);

        measureScheduler.scheduledRead();

        verify(scheduledExecutorService).scheduleAtFixedRate(any(Runnable.class),
                eq(INITIAL_DELAY),
                same(SECONDS_TO_TRAVEL_100_METERS_AT_3MS),
                eq(TimeUnit.SECONDS));
    }

    @Test
    public void report_every_15_minutes() {
        Robot robot = mock(Robot.class);
        ScheduledExecutorService scheduledExecutorService = mock(ScheduledExecutorService.class);
        MeasureScheduler measureScheduler = new MeasureScheduler(robot, scheduledExecutorService);

        measureScheduler.scheduleReport();

        verify(scheduledExecutorService).scheduleAtFixedRate(any(Runnable.class),
                eq(INITIAL_DELAY),
                same(MINUTES_TO_REPORT),
                eq(TimeUnit.MINUTES));
    }
}
