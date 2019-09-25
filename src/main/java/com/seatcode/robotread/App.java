package com.seatcode.robotread;

import com.seatcode.robotread.actions.ReadMeasure;
import com.seatcode.robotread.api.Console;
import com.seatcode.robotread.api.ReportFormatter;
import com.seatcode.robotread.api.decoder.PolylineDecoder;
import com.seatcode.robotread.domain.model.Clock;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.domain.services.MeasureScheduler;
import com.seatcode.robotread.domain.services.ReportPrinter;
import com.seatcode.robotread.infrastructure.ReadLevel;
import com.seatcode.robotread.repository.MeasureRepository;

import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class App {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        ReportFormatter reportFormatter = new ReportFormatter();
        Console console = new Console();
        ReportPrinter reportPrinter = new ReportPrinter(console, reportFormatter);
        LinkedHashMap<Long, Record> map = new LinkedHashMap<>();
        Clock clock =new Clock();
        MeasureRepository measureRepository = new MeasureRepository(map, clock);
        ReadLevel readLevel = new ReadLevel();
        ReadMeasure readMeasure = new ReadMeasure(readLevel, measureRepository, reportPrinter);
        MeasureScheduler measureScheduler = new MeasureScheduler(readMeasure, scheduledExecutorService);
        //ctn{Fsy`LmHxG
        String polylineInput = "ctn{Fsy`LmHxG";
        PolylineDecoder polylineDecoder = new PolylineDecoder(polylineInput);
        readMeasure.start(polylineDecoder);
        measureScheduler.scheduledRead();
        measureScheduler.scheduleReport();
    }
}
