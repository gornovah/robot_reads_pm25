package com.seatcode.robotread.actions;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.actions.Robot;
import com.seatcode.robotread.api.decoder.PolylineDecoder;
import com.seatcode.robotread.domain.services.ReportPrinter;
import com.seatcode.robotread.domain.model.Average;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.infrastructure.ReadLevel;
import com.seatcode.robotread.repository.MeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RobotShould {

    private PolylineDecoder polylineDecoder;
    private ReadLevel readLevel;
    private MeasureRepository measureRepository;
    private ReportPrinter reportPrinter;
    private Robot robot;
    private LatLng position;
    private int measure;
    private long instant;

    @Before
    public void setUp() throws Exception {
        polylineDecoder = new PolylineDecoder("{bp{F}vbLgIeP");
        readLevel = mock(ReadLevel.class);
        measureRepository = mock(MeasureRepository.class);
        reportPrinter = mock(ReportPrinter.class);
        robot = new Robot(readLevel, measureRepository, reportPrinter);
        position = new LatLng(51.23241, -0.1223);
        measure = 140;
        instant = 1528106219;
    }

    @Test
    public void move_through_polyline() {
        robot.start(polylineDecoder);

        String position = robot.position().toString();
        assertThat(position).isEqualTo("41.37698000,2.15186000");
    }

    @Test
    public void read_pm25_level() {

        robot.start(polylineDecoder);

        robot.readPm25Level();
        verify(readLevel).execute();
    }

    @Test
    public void save_reading_pm25_level() {

        robot.start(polylineDecoder);
        robot.readPm25Level();
        ArgumentCaptor<Record> argumentCaptor = ArgumentCaptor.forClass(Record.class);

        verify(measureRepository).save(argumentCaptor.capture());
    }

    @Test
    public void report_the_readings_of_pm25_level() {
        Average average = new Average(measure, position, instant, "robot");
        given(measureRepository.load()).willReturn(average);

        robot.start(polylineDecoder);
        robot.reportMeasure();

        verify(measureRepository).load();
        verify(reportPrinter).report(average);
    }

}