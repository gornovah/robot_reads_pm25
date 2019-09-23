package com.seatcode.robotread.repository;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.Record;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;


public class MeasureRepositoryShould {

    private long id;

    @Before
    public void setUp() throws Exception {
        id = 1L;
    }

    @Test
    public void save_read_pm25_level() {
        HashMap<Long, Record> map = new HashMap<>();
        LatLng position = new LatLng(51.23241, -0.1223);
        String measure = "USG";
        long instant = 1528106219;
        Record record = new Record(id, measure, position, instant, "robot");
        MeasureRepository measureRepository = new MeasureRepository(map);

        measureRepository.save(record);

        assertThat(map.containsKey(id)).isTrue();
    }
    
  /*  @Test
    public void load_the_average_of_records() {


    }*/

}