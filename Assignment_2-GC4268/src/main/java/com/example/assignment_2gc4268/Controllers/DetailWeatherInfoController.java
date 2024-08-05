
package com.example.assignment_2gc4268.Controllers;

import com.example.assignment_2gc4268.Model.Weather;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DetailWeatherInfoController {

    @FXML
    private Label cityLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label precipitationLabel;

    @FXML
    private Label rainChanceLabel;

    @FXML
    private Label windKphLabel;

    @FXML
    private Label minTempLabel;

    @FXML
    private Label maxTempLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label cityUvLabel;

    @FXML
    private Button backButton;

    @FXML
    private void handleBackButton(ActionEvent event) {
        ((Stage) backButton.getScene().getWindow()).close();
    }

    public void setWeatherDetails(Weather weather) {
        cityLabel.setText("City: " + weather.getCityName());
        temperatureLabel.setText("Temperature: " + weather.getCurrentTemperature() + "°C");
        humidityLabel.setText("Humidity: " + weather.getCurrentHumidity() + "%");
        precipitationLabel.setText("Precipitation: " + weather.getCurrentPrecipitation() + "mm");
        rainChanceLabel.setText("Daily Chance of Rain: " + weather.getDailyRain());
        windKphLabel.setText("Wind Speed: " + weather.getWindKph() + " kph");
        minTempLabel.setText("Minimum Temperature: " + weather.getMinTemperature() + "°C");
        maxTempLabel.setText("Maximum Temperature: " + weather.getMaxTemperature() + "°C");
        statusLabel.setText("Whole Day Condition: " + weather.getCondition());
        cityUvLabel.setText("City UV Concentration: " + weather.getCityUV());
    }
}

