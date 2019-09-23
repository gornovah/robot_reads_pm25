package com.seatcode.robotread;


import com.google.maps.model.LatLng;
import com.seatcode.robotread.domain.model.Clock;
import com.seatcode.robotread.repository.MeasureRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RobotReadFeature {

    String polylineInput = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGu\n" +
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
    private PolylineRoute polylineRoute;
    private Robot robot;
    private LatLng position;
    private String measure ;
    private long instant;
    private Console console;
    private ReportFormatter reportFormatter;
    private HashMap<Long, Record> map;

    @Before
    public void setUp() throws Exception {
        reportPrinter = new ReportPrinter(console, reportFormatter);
        timestamp = mock(Clock.class);
        map = new HashMap<>();
        measureRepository = new MeasureRepository(map);
        readLevel = new ReadLevel();
        polylineRoute = new PolylineRoute(polylineInput);
        position = new LatLng(51.23241, -0.1223);
        measure = "USG";
        instant = 1528106219;
    }

    @Test
    public void return_the_report_of_new_readings() {

        Record record = new Record(null, measure, position, instant, "robot");
        given(timestamp.timestampAsString()).willReturn("1528106219");

        robot = new Robot(readLevel, measureRepository, reportPrinter);

        robot.start(polylineRoute);
        robot.readPm25Level();
        robot.reportMeasure();


        verify(reportPrinter).report(record);
    }
}
