package com.seatcode.robotread;

import com.seatcode.robotread.actions.RobotSystem;
import com.seatcode.robotread.api.Console;
import com.seatcode.robotread.api.ReportFormatter;
import com.seatcode.robotread.api.decoder.PolylineDecoder;
import com.seatcode.robotread.domain.services.RouteService;
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
        Clock clock = new Clock();
        MeasureRepository measureRepository = new MeasureRepository(map, clock);
        ReadLevel readLevel = new ReadLevel();
        RouteService routeService = new RouteService();
        RobotSystem robotSystem = new RobotSystem(readLevel, measureRepository, reportPrinter, routeService);
        MeasureScheduler measureScheduler = new MeasureScheduler(robotSystem, scheduledExecutorService);
        //ctn{Fsy`LmHxG
        String polylineInput = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGu\n" +
                "AD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa\n" +
                "@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkI\n" +
                "CmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^y\n" +
                "VJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSM\n" +
                "GBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@\n" +
                "a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iD\n" +
                "q@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQ\n" +
                "KkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
        PolylineDecoder polylineDecoder = new PolylineDecoder(polylineInput);
        robotSystem.start(polylineDecoder);
        measureScheduler.scheduledRead();
        measureScheduler.scheduleReport();

        int totalIterations = routeService.totalIterations(polylineDecoder.decode());
        try {
            measureScheduler.stopScheduledExecutorService(totalIterations);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
