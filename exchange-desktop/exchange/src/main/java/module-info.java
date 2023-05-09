module com.mel.exchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    opens com.mel.exchange to javafx.fxml;
    opens com.mel.exchange.api.model to javafx.base, gson;
    exports com.mel.exchange;
    exports com.mel.exchange.rates;
    requires java.prefs;
    opens com.mel.exchange.rates to javafx.fxml;
    opens com.mel.exchange.login to javafx.fxml;
    opens com.mel.exchange.register to javafx.fxml;
    opens com.mel.exchange.transactions to javafx.fxml;
    opens com.mel.exchange.statistics to javafx.fxml;
    opens com.mel.exchange.requests to javafx.fxml;

}