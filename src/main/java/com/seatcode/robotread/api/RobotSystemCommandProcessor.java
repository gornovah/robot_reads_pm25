package com.seatcode.robotread.api;

import com.seatcode.robotread.actions.RobotSystem;
import com.seatcode.robotread.api.decoder.PolylineDecoder;
import com.seatcode.robotread.domain.exceptions.RobotException;
import com.seatcode.robotread.domain.services.MeasureScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
/**
 * Class that executes commands
 * @author despinosa
 */
public class RobotSystemCommandProcessor {

    private MeasureScheduler measureScheduler;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(0);
    private PolylineDecoder polylineDecoder;

    /**
     * Proccesor of commands that recive from the input of console
     * @param robotSystem
     * @param polylineInput
     * @param command
     * @throws RobotException error process commands
     * @throws InterruptedException error to shutdown scheduleExecutorServices
     *
     * @author despinosa
     */
    public void proccessCommands(RobotSystem robotSystem, String polylineInput, String command) throws InterruptedException {
        measureScheduler = new MeasureScheduler(robotSystem, scheduledExecutorService);
        polylineDecoder = new PolylineDecoder(polylineInput);

        switch (command) {
            case "start_robot_system":
                startRobotSystem(robotSystem);
                break;
            case "stop_robot_sytem":
                stopRobotSystem();
                break;
            case "report_measure":
                robotSystem.reportMeasure();
                break;
            default:
                throw new RobotException("Error process commands");
        }
    }

    private void startRobotSystem(RobotSystem robotSystem) {
        robotSystem.start(polylineDecoder);
        measureScheduler.scheduledRead();
        measureScheduler.scheduleReport();
    }

    private void stopRobotSystem() throws InterruptedException {
        measureScheduler.stopScheduledExecutorService();
    }
}
