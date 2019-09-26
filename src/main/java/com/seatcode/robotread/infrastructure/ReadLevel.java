package com.seatcode.robotread.infrastructure;

import java.util.Random;
/**
 * The class that simulate the sensor for read of pm2.5
 *
 * @author despinosa
 */
public class ReadLevel {

    public int execute() {
        return getRandomNumber();
    }

    private int getRandomNumber (){
        Random r = new Random();
        return r.ints(0, 151).findFirst().getAsInt();
    }

}
