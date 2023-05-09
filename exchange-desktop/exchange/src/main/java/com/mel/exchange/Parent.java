package com.mel.exchange;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Parent implements Initializable, OnPageCompleteListener {
    public BorderPane borderPane;
    public Button transactionButton;
    public Button loginButton;
    public Button registerButton;
    public Button logoutButton;

    public Button statsButton;
    public Button postingsButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateNavigation();
    }

    public void ratesSelected() {
        swapContent(Section.RATES);
    }

    public void transactionsSelected() {
        swapContent(Section.TRANSACTIONS);
    }

    public void loginSelected() {
        swapContent(Section.LOGIN);
    }

    public void registerSelected() {
        swapContent(Section.REGISTER);
    }

    public void statsSelected() {
        swapContent(Section.STATISTICS);
    }

    public void postingsSelected(){
        swapContent(Section.POSTINGS);
    }
    public void logoutSelected() {
        Authentication.getInstance().deleteToken();
        swapContent(Section.RATES);
    }
    private void swapContent(Section section) {
        try {
            URL url = getClass().getResource(section.getResource());
            System.out.println(url);
            FXMLLoader loader = new FXMLLoader(url);
            borderPane.setCenter(loader.load());
            if (section.doesComplete()) {
                ((PageCompleter)
                        loader.getController()).setOnPageCompleteListener(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateNavigation();
    }

    private enum Section {
        RATES,
        TRANSACTIONS,
        LOGIN,
        REGISTER,

        POSTINGS,
        STATISTICS;

        public boolean doesComplete() {
            return switch (this) {
                case LOGIN, REGISTER -> true;
                default -> false;
            };
        }

        public String getResource() {
            return switch (this) {
                case RATES -> "/com/mel/exchange/rates/rates.fxml";
                case TRANSACTIONS -> "/com/mel/exchange/transactions/transactions.fxml";
                case LOGIN -> "/com/mel/exchange/login/login.fxml";
                case REGISTER -> "/com/mel/exchange/register/register.fxml";
                case STATISTICS -> "/com/mel/exchange/statistics/statistics.fxml";
                case POSTINGS -> "/com/mel/exchange/postings/postings.fxml";
                default -> null;
            };
        }
    }

    @Override
    public void onPageCompleted() {
        swapContent(Section.RATES);
    }

    private void updateNavigation() {
        boolean authenticated = Authentication.getInstance().getToken() != null;

        transactionButton.setManaged(authenticated);
        transactionButton.setVisible(authenticated);
        loginButton.setManaged(!authenticated);
        loginButton.setVisible(!authenticated);
        registerButton.setManaged(!authenticated);
        registerButton.setVisible(!authenticated);
        logoutButton.setManaged(authenticated);
        logoutButton.setVisible(authenticated);
        statsButton.setManaged(authenticated);
        statsButton.setVisible(authenticated);
        postingsButton.setManaged(authenticated);
        postingsButton.setVisible(authenticated);
    }
}