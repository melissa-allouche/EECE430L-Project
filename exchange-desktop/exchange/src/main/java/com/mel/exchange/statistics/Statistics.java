package com.mel.exchange.statistics;
import com.mel.exchange.api.ExchangeService;
import com.mel.exchange.api.model.ExchangeRates;
import com.mel.exchange.api.model.Stats;
import com.mel.exchange.api.model.Graph;
import javafx.application.Platform;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Statistics {
    public Label buyUsdRateLabel;
    public Label sellUsdRateLabel;
    public Label srChange;
    public Label brChange;
    public Label brChangeM;
    public Label srChangeM;
    public Label maxSell;
    public Label maxBuy;
    public Label maxSellDate;
    public Label maxBuyDate;

    public Label minSell;
    public Label minBuy;
    public Label minSellDate;
    public Label minBuyDate;
    public LineChart graph;
    public NumberAxis yAxis;
    public CategoryAxis xAxis;

    public XYChart.Series<Number, Number> series1;
    public XYChart.Series<Number, Number>  series2;


    public void initialize() {
        fetchRates();
        fetchStats();
        fetchGraph();
    }

    private void fetchRates() {
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(Call<ExchangeRates> call, Response<ExchangeRates> response) {
                ExchangeRates exchangeRates = response.body();
                Platform.runLater(() -> {
                    buyUsdRateLabel.setText(exchangeRates.lbpToUsd.toString());
                    sellUsdRateLabel.setText(exchangeRates.usdToLbp.toString());
                });
            }

            @Override
            public void onFailure(Call<ExchangeRates> call, Throwable throwable) {
            }
        });


    }

    private void fetchStats() {
        ExchangeService.exchangeApi().getStats().enqueue(new Callback<Stats>() {
            @Override
            public void onResponse(Call<Stats> call, Response<Stats> response) {
                Stats stats = response.body();
                Platform.runLater(() -> {
                    String increase="Increased by ";
                    String decrease="Decreased by ";
                    if (stats.delta_buy_usd_day >0) {
                        Float abs_buy=Math.abs(stats.delta_buy_usd_day);
                        brChange.setText(increase + abs_buy.toString());
                    } else if (stats.delta_buy_usd_day <0) {
                        Float abs_buy=Math.abs(stats.delta_buy_usd_day);
                        brChange.setText(decrease + abs_buy.toString());
                    }
                    else{
                        brChange.setText("No change since yesterday");
                    }

                    if (stats.delta_sell_usd_day>0) {
                        Float abs_sell=Math.abs(stats.delta_sell_usd_day);
                        srChange.setText(increase + abs_sell.toString());
                    } else if (stats.delta_sell_usd_day<0) {
                        Float abs_sell=Math.abs(stats.delta_sell_usd_day);
                        srChange.setText(decrease + abs_sell.toString());
                    }
                    else{
                        srChange.setText("No change since yesterday");
                    }
                    System.out.println(stats.delta_buy_usd_month);
                    if (stats.delta_buy_usd_month >0) {
                        Float abs_buy=Math.abs(stats.delta_buy_usd_month);
                        brChangeM.setText(increase + abs_buy.toString());
                    } else if (stats.delta_buy_usd_month <0) {
                        Float abs_buy=Math.abs(stats.delta_buy_usd_month);
                        brChangeM.setText(decrease + abs_buy.toString());
                    }
                    else{
                        brChangeM.setText("No change since last month");
                    }

                    if (stats.delta_sell_usd_month>0) {
                        Float abs_sell=Math.abs(stats.delta_sell_usd_month);
                        srChangeM.setText(increase + abs_sell.toString());
                    } else if (stats.delta_sell_usd_month<0) {
                        Float abs_sell=Math.abs(stats.delta_sell_usd_month);
                        srChangeM.setText(decrease + abs_sell.toString());
                    }
                    else{
                        srChangeM.setText("No change since last month");
                    }

                    maxBuy.setText( stats.max_buy_usd_rate.toString());
                    maxSell.setText( stats.max_sell_usd_rate.toString());
                    maxBuyDate.setText( stats.max_buy_usd_rate_DATE.toString());
                    maxSellDate.setText( stats.max_sell_usd_rate_DATE.toString());

                    minBuy.setText( stats.min_buy_usd_rate.toString());
                    minSell.setText( stats.min_sell_usd_rate.toString());
                    minBuyDate.setText( stats.min_buy_usd_rate_DATE.toString());
                    minSellDate.setText( stats.min_sell_usd_rate_DATE.toString());

                });
            }

            @Override
            public void onFailure(Call<Stats> call, Throwable throwable) {
            }
        });
    }

    private void fetchGraph() {
        ExchangeService.exchangeApi().getGraph().enqueue(new Callback<Graph>() {
            @Override
            public void onResponse(Call<Graph> call, Response<Graph> response) {
                Graph graphing = response.body();
                Platform.runLater(() -> {
                    graph.setAnimated(false);
                    graph.setTitle("Exchange Rate Graph");
                    yAxis.setLabel("Exchange Rate");
                    xAxis.setLabel("Time");



                    series1 = new XYChart.Series();
                    series1.setName("Buy USD Rate");
                    for (int i = 0; i < graphing.buy_usd_rates.size(); i++) {
                        series1.getData().add(new XYChart.Data(graphing.getDays().get(i), graphing.getBuy_usd_rates().get(i)));
                    }
                    series2 = new XYChart.Series();
                    series2.setName("Sell USD Rate");
                    for (int i = 0; i < graphing.sell_usd_rates.size(); i++) {
                        series2.getData().add(new XYChart.Data(graphing.getDays().get(i), graphing.getSell_usd_rates().get(i)));
                    }
                    // Clear existing data in the LineChart
                    graph.getData().clear();
                    // Add the series to the LineChart
                    graph.getData().addAll(series1, series2);
                });
            }

            @Override
            public void onFailure(Call<Graph> call, Throwable throwable) {
            }

        });
    }

}