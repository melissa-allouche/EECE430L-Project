package com.mel.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class exchangeRequest {
    @SerializedName("sell")
    public Boolean sell;
    @SerializedName("usd")
    public Boolean usd;
    @SerializedName("amount")
    public Float amount;

    @SerializedName("id")
    public Integer id;

    @SerializedName("user_id")
    public Integer user_id;
    @SerializedName("location")
    public String location;

    public exchangeRequest( Integer id, Boolean sell, Boolean usd, Float amount, String location, Integer user_id)
    {
        this.id=id;
        this.sell = sell;
        this.usd = usd;
        this.amount = amount;
        this.location= location;
        this.user_id=user_id;
    }
    public exchangeRequest(Boolean sell, Boolean usd, Float amount, String location) {
        this(null, sell, usd, amount, location, null);
    }
    public Float getAmount() {
        return amount;
    }

    public Boolean getSell() {
        return sell;
    }

    public Boolean getUsd() {
        return usd;
    }

    public String getLocation() {
        return location;
    }

    public Integer getUserId() {
        return user_id;
    }


    public Integer getId() {
        return id;
    }
}

