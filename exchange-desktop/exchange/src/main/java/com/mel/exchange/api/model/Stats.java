package com.mel.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("delta_sell_usd_day")
    public Float delta_sell_usd_day;
    @SerializedName("delta_buy_usd_day")
    public Float delta_buy_usd_day;

    @SerializedName("delta_sell_usd_month")
    public Float delta_sell_usd_month;

    @SerializedName("delta_buy_usd_month")
    public Float delta_buy_usd_month;

    @SerializedName("max_sell_usd_rate")
    public Float max_sell_usd_rate;

    @SerializedName("max_buy_usd_rate")
    public Float max_buy_usd_rate;
    @SerializedName("max_sell_usd_rate_DATE")
    public String max_sell_usd_rate_DATE;

    @SerializedName("max_buy_usd_rate_DATE")
    public String max_buy_usd_rate_DATE;

    @SerializedName("min_sell_usd_rate")
    public Float min_sell_usd_rate;

    @SerializedName("min_buy_usd_rate")
    public Float min_buy_usd_rate;
    @SerializedName("min_sell_usd_rate_DATE")
    public String min_sell_usd_rate_DATE;

    @SerializedName("min_buy_usd_rate_DATE")
    public String min_buy_usd_rate_DATE;

}
