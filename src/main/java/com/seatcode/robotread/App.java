package com.seatcode.robotread;

import com.seatcode.robotread.actions.Robot;
import com.seatcode.robotread.domain.services.MeasureScheduler;

import java.util.concurrent.ScheduledExecutorService;

public class App {
    public static void main(String[] args) {
        Robot robot = null;
        ScheduledExecutorService scheduledExecutorService = null;
        MeasureScheduler measureScheduler = new MeasureScheduler(robot, scheduledExecutorService);
        measureScheduler.scheduledRead();

    }
}
