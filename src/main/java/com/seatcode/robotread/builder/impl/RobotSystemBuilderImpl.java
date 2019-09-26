package com.seatcode.robotread.builder.impl;

import com.seatcode.robotread.actions.RobotSystem;
import com.seatcode.robotread.api.Console;
import com.seatcode.robotread.api.ReportFormatter;
import com.seatcode.robotread.builder.RobotSystemBuilder;
import com.seatcode.robotread.domain.model.Clock;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.domain.services.ReportPrinter;
import com.seatcode.robotread.domain.services.RouteService;
import com.seatcode.robotread.infrastructure.ReadLevel;
import com.seatcode.robotread.repository.MeasureRepository;

import java.util.LinkedHashMap;

public class RobotSystemBuilderImpl implements RobotSystemBuilder {
    @Override
    public RobotSystem build() {
        ReportFormatter reportFormatter = new ReportFormatter();
        Console console = new Console();
        ReportPrinter reportPrinter = new ReportPrinter(console, reportFormatter);
        LinkedHashMap<Long, Record> map = new LinkedHashMap<>();
        Clock clock = new Clock();
        MeasureRepository measureRepository = new MeasureRepository(map, clock);
        ReadLevel readLevel = new ReadLevel();
        RouteService routeService = new RouteService();
        return new RobotSystem(readLevel, measureRepository, reportPrinter, routeService);
    }
}
