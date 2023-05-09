package com.mel.exchange.api;

import com.google.gson.JsonObject;
import com.mel.exchange.api.model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface Exchange {
    @POST("/user")
    Call<User> addUser(@Body User user);
    @POST("/authentication")
    Call<Token> authenticate(@Body User user);
    @GET("/exchangeRate")
    Call<ExchangeRates> getExchangeRates();
    @POST("/transaction")Call<Object> addTransaction(@Body Transaction transaction,
                                                     @Header("Authorization") String authorization);
    @GET("/transaction")
    Call<List<Transaction>> getTransactions(@Header("Authorization")
                                            String authorization);
    @GET("/getStats")
    Call<Stats> getStats ();

    @GET("/getGraph")
    Call<Graph> getGraph ();

    @GET("/getExchangeRequest")
    Call <List<exchangeRequest>> getExchangeRequest (@Header("Authorization")  String authorization);

    @POST("/postExchangeRequest")
    Call<Object> addExchangeRequest (@Body exchangeRequest exchangeRequest,
                                         @Header("Authorization") String authorization);

    @POST("/acceptExchangeRequest")
    Call<Object> acceptExchangeRequest (@Body JsonObject request,
                             @Header("Authorization") String authorization);

    @GET("/getWallet")
    Call<Wallet> getWallet (@Header("Authorization") String authorization);

}