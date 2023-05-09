package com.mel.exchange.login;

import com.mel.exchange.Authentication;
import com.mel.exchange.OnPageCompleteListener;
import com.mel.exchange.PageCompleter;
import com.mel.exchange.api.ExchangeService;
import com.mel.exchange.api.model.Token;
import com.mel.exchange.api.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login implements PageCompleter {
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    private OnPageCompleteListener onPageCompleteListener;

    public void login(ActionEvent actionEvent) {
        User user = new User(usernameTextField.getText(), passwordTextField.getText());
        ExchangeService.exchangeApi().authenticate(user).enqueue(new Callback<Token>() {
             @Override
             public void onResponse(Call<Token> call, Response<Token>
                     response) {
                 Authentication.getInstance().saveToken(response.body().getToken());
                 Platform.runLater(() -> {
                     onPageCompleteListener.onPageCompleted();
                 });
             }

             @Override
             public void onFailure(Call<Token> call, Throwable throwable) {
             }
         });
    }

    public void setOnPageCompleteListener(OnPageCompleteListener
                                                  onPageCompleteListener) {
        this.onPageCompleteListener = onPageCompleteListener;
    }
}