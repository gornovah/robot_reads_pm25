package com.seatcode.robotread.acceptance;


import com.seatcode.robotread.actions.RobotSystem;
import com.seatcode.robotread.api.Console;
import com.seatcode.robotread.api.ReportFormatter;
import com.seatcode.robotread.api.decoder.PolylineDecoder;
import com.seatcode.robotread.domain.model.Clock;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.domain.services.ReportPrinter;
import com.seatcode.robotread.domain.services.RouteService;
import com.seatcode.robotread.infrastructure.ReadLevel;
import com.seatcode.robotread.repository.MeasureRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.LinkedHashMap;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RobotSystemFeature {

    private final String polylineInput = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGu\n" +
            "AD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa\n" +
            "@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkI\n" +
            "CmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^y\n" +
            "VJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSM\n" +
            "GBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@\n" +
            "a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iD\n" +
            "q@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQ\n" +
            "KkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
    private ReportPrinter reportPrinter;
    private Clock timestamp;
    private MeasureRepository measureRepository;
    private ReadLevel readLevel;
    private PolylineDecoder polylineDecoder;
    private RobotSystem robotSystem;
    private Console console;
    private ReportFormatter reportFormatter;
    private LinkedHashMap<Long, Record> map;
    private Clock clock;

    @Before
    public void setUp() {
        console = mock(Console.class);
        reportFormatter = new ReportFormatter();
        reportPrinter = new ReportPrinter(console, reportFormatter);
        timestamp = mock(Clock.class);
        map = new LinkedHashMap<>();
        clock = mock(Clock.class);
        measureRepository = new MeasureRepository(map, clock);
        readLevel = mock(ReadLevel.class);
        polylineDecoder = new PolylineDecoder(polylineInput);
    }

    @Test
    public void return_the_report_of_new_readings() {

        RouteService routeService = new RouteService();
        given(timestamp.timestampAsString()).willReturn("1528106219");
        given(clock.instantNow()).willReturn(Instant.ofEpochMilli(1528106219));
        given(readLevel.execute()).willReturn(90);
        robotSystem = new RobotSystem(readLevel, measureRepository, reportPrinter, routeService);

        robotSystem.start(polylineDecoder);
        robotSystem.readPm25Level();
        robotSystem.reportMeasure();

        verify(console).print("{\"level\":\"MODERATE\",\"location\":{\"lng\":-0.21533000000000002,\"lat\":51.504870000000004},\"source\":\"robot\",\"timestamp\":1528106219}");

    }
}
