package com.harambe.refresherauto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandeep on 05-01-2017.
 */

public class DateItem {
    String time;
    @SerializedName("milliseconds_since_epoch")
    Long millis;
    String date;


    @Override
    public String toString() {
        return "Time:  "+time+"\nMillis:  "+millis+"\nDate:  "+date;
    }
}
