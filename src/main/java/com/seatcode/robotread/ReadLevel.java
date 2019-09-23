package com.seatcode.robotread;

import java.util.Random;

public class ReadLevel {

    public int execute() {
        return getRandomNumber();
    }

    private int getRandomNumber (){
        Random r = new Random();
        return r.ints(0, 151).findFirst().getAsInt();
    }

}
