package com.mel.exchange.api.model;


import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Graph {

    @SerializedName("days")
    public List<String> days;
    @SerializedName("sell_usd_rates")
    public List<Float> sell_usd_rates;

    @SerializedName("buy_usd_rates")
    public List<Float> buy_usd_rates;

    public List<Float> getBuy_usd_rates() {
        return buy_usd_rates;
    }

    public List<Float> getSell_usd_rates() {
        return sell_usd_rates;
    }
    public List<String> getDays() {
        List<String> newDays= new ArrayList<String>();
        for( String day: days) {
             String [] split= day.split(" ");
            System.out.println(split);
             newDays.add(split[1]+" "+ split[2]);
        }
        return newDays;
    }
}
