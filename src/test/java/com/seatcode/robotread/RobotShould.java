package com.seatcode.robotread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RobotShould {

    private PolylineRoute polylineRoute;
    private ReadLevel readLevel;
    private MeasureRepository measureRepository;
    private ReportPrinter reporPrinter;
    private Robot robot;

    @Before
    public void setUp() throws Exception {
        polylineRoute = new PolylineRoute("{bp{F}vbLgIeP");
        readLevel = mock(ReadLevel.class);
        measureRepository = mock(MeasureRepository.class);
        reporPrinter = mock(ReportPrinter.class);
        robot = new Robot(readLevel, measureRepository, reporPrinter);
    }

    @Test
    public void move_through_polyline() {
        robot.start(polylineRoute);

        String position = robot.position().toString();
        assertThat(position).isEqualTo("41.37698000,2.15186000");
    }

    @Test
    public void read_pm25_level() {

        robot.start(polylineRoute);

        robot.readPm25Level();
        verify(readLevel).execute();
    }
    
    @Test
    public void save_reading_pm25_level() {

        robot.start(polylineRoute);
        robot.readPm25Level();
        ArgumentCaptor<Record> argumentCaptor = ArgumentCaptor.forClass(Record.class);

        verify(measureRepository).save(argumentCaptor.capture());
    }
    
    @Test
    public void report_the_readings_of_pm25_level() {
        String text = "{timestamp:1528106219,location:{lat:51.23241,lng:-0.1223},level:\"USG\",source:\"robot\"}";
        given(measureRepository.load()).willReturn("{timestamp:1528106219,location:{lat:51.23241,lng:-0.1223},level:\"USG\",source:\"robot\"}");

        robot.start(polylineRoute);
        robot.reportMeasure();

        verify(measureRepository).load();
        verify(reporPrinter).report(text);
    }

}