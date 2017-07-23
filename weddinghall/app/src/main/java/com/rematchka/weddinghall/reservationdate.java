package com.rematchka.weddinghall;

/**
 * Created by extra on 26/12/2016.
 */
public class reservationdate {
    String date;
    String slot;
    reservationdate(String d,String s)
    {
        date=d;slot=s;
    }
    String getDate()
    {
        return date;
    }
    String getSlot()
    {
        return slot;
    }
}
