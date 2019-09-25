package com.seatcode.robotread.actions;

import com.google.maps.model.LatLng;
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

public class ReadMeasureShould {

    private PolylineDecoder polylineDecoder;
    private ReadLevel readLevel;
    private MeasureRepository measureRepository;
    private ReportPrinter reportPrinter;
    private ReadMeasure readMeasure;
    private LatLng position;
    private int measure;
    private long instant;

    @Before
    public void setUp() {
        polylineDecoder = new PolylineDecoder("{bp{F}vbLgIeP");
        readLevel = mock(ReadLevel.class);
        measureRepository = mock(MeasureRepository.class);
        reportPrinter = mock(ReportPrinter.class);
        readMeasure = new ReadMeasure(readLevel, measureRepository, reportPrinter);
        position = new LatLng(51.23241, -0.1223);
        measure = 140;
        instant = 1528106219;
    }

    @Test
    public void move_through_polyline() {
        readMeasure.start(polylineDecoder);

        String position = readMeasure.position().toString();
        assertThat(position).isEqualTo("41.37698000,2.15186000");
    }

    @Test
    public void read_pm25_level() {

        readMeasure.start(polylineDecoder);

        readMeasure.readPm25Level();
        verify(readLevel).execute();
    }

    @Test
    public void save_reading_pm25_level() {

        readMeasure.start(polylineDecoder);
        readMeasure.readPm25Level();
        ArgumentCaptor<Record> argumentCaptor = ArgumentCaptor.forClass(Record.class);

        verify(measureRepository).save(argumentCaptor.capture());
    }

    @Test
    public void report_the_readings_of_pm25_level() {
        Average average = new Average(measure, position, instant, "readMeasure");
        given(measureRepository.load()).willReturn(average);

        readMeasure.start(polylineDecoder);
        readMeasure.reportMeasure();

        verify(measureRepository).load();
        verify(reportPrinter).report(average);
    }

    

}