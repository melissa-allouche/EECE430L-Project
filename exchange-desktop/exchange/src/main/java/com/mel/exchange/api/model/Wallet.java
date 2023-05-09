package com.mel.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class Wallet {
    @SerializedName("id")
    public Integer id;
    @SerializedName("usd_balance")
    public Float usd_balance;
    @SerializedName("lbp_balance")
    public Float lbp_balance;
    public Wallet( Float usd_balance, Float lbp_balance) {
        this.usd_balance = usd_balance;
        this.lbp_balance = lbp_balance;
    }
}

