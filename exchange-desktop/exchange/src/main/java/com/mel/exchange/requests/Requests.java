package com.mel.exchange.requests;

import com.google.gson.JsonObject;
import com.mel.exchange.Authentication;
import com.mel.exchange.api.ExchangeService;
import com.mel.exchange.api.model.exchangeRequest;
import com.mel.exchange.api.model.Wallet;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Requests implements Initializable {
    public TableColumn usdOrLbp;
    public TableColumn sellOrBuy;
    public TableColumn amount;
    public TableColumn location;
    public TableColumn requestID;
    public TableColumn userID;
    public TableView tableView;

    public Label usdWallet;

    public Label lbpWallet;
    public TextField chosenID;

    public TextField amountTextField;
    public TextField locationTextField;
    public ToggleGroup postingType;
    public ToggleGroup postingInputType;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setUp();

        //action.setCellValueFactory(new PropertyValueFactory);
        getRequests();
        getWallet();
    }

    private void setUp(){

        requestID.setCellValueFactory(new
                PropertyValueFactory<exchangeRequest, Integer>("id"));
        sellOrBuy.setCellValueFactory(new
                PropertyValueFactory<exchangeRequest, Float>("sell"));
        usdOrLbp.setCellValueFactory(new
                PropertyValueFactory<exchangeRequest, Boolean>("usd"));
        amount.setCellValueFactory(new
                PropertyValueFactory<exchangeRequest, Float>("amount"));
        location.setCellValueFactory(new
                PropertyValueFactory<exchangeRequest, String>("location"));
        //userID.setCellValueFactory(new PropertyValueFactory<exchangeRequest, Integer>("user_id"));
    }

    private void getRequests() {
        ExchangeService.exchangeApi().getExchangeRequest("Bearer " + Authentication.getInstance().getToken()).enqueue(new Callback<List<exchangeRequest>>() {
            @Override
            public void onResponse(Call<List<exchangeRequest>> call,
                                   Response<List<exchangeRequest>> response) {
                System.out.println(response.body());
                tableView.getItems().setAll(response.body());
            }

            @Override
            public void onFailure(Call<List<exchangeRequest>> call,
                                  Throwable throwable) {
            }

        });
    }

    public void addPosting(ActionEvent actionEvent) {
        exchangeRequest exchangeRequest = new exchangeRequest(
                ((RadioButton) postingType.getSelectedToggle()).getText().equals("Sell"),
                ((RadioButton) postingInputType.getSelectedToggle()).getText().equals("USD"),
                Float.parseFloat(amountTextField.getText()),
                locationTextField.getText()
        );

        String userToken = Authentication.getInstance().getToken();
        String authHeader = userToken != null ? "Bearer " + userToken : null;
        ExchangeService.exchangeApi().addExchangeRequest(exchangeRequest, authHeader).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                getRequests();
                Platform.runLater(() -> {
                    amountTextField.setText("");
                    locationTextField.setText("");
                });
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
            }
        });
    }

    public void acceptPosting(ActionEvent actionEvent) {

        String userToken = Authentication.getInstance().getToken();
        String authHeader = userToken != null ? "Bearer " + userToken : null;
        Integer request_id= Integer.parseInt(chosenID.getText());
        JsonObject request = new JsonObject();
        request.addProperty("request_id", request_id);

        ExchangeService.exchangeApi().acceptExchangeRequest(request, authHeader).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                setUp();
                getRequests();
                getWallet();
                Platform.runLater(() -> {
                    chosenID.setText("");
                });
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
            }
        });
    }

    public void getWallet(){
        ExchangeService.exchangeApi().getWallet("Bearer " + Authentication.getInstance().getToken()).enqueue(new Callback<Wallet>() {
            @Override
            public void onResponse(Call<Wallet> call,
                                   Response<Wallet> response) {
                Wallet wallet= response.body();
                Platform.runLater(() -> {
                    usdWallet.setText("USD: $ "+wallet.usd_balance.toString());
                    lbpWallet.setText("LBP: "+wallet.lbp_balance.toString()+" LBP");
                });
            }

            @Override
            public void onFailure(Call<Wallet> call,
                                  Throwable throwable) {
            }

        });
    }
}

