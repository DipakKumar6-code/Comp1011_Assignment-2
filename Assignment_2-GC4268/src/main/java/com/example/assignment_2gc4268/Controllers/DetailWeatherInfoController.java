package com.example.assignment_2gc4268.Controllers;

import com.example.assignment_2gc4268.HelloApplication;
import com.example.assignment_2gc4268.Model.Weather;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

/**
 * Controller for the detailed weather information view.
 */
public class DetailWeatherInfoController {

    private HelloApplication app;  // Reference to the main application

    // FXML UI elements
    @FXML
    private Label detailWeatherLabel;  // Displays the title of the weather detail
    @FXML
    private Label cityLabel;           // Displays the city name
    @FXML
    private Label temperatureLabel;    // Displays the current temperature
    @FXML
    private Label humidityLabel;       // Displays the current humidity
    @FXML
    private Label precipitationLabel;  // Displays current precipitation
    @FXML
    private Label rainChanceLabel;     // Displays chance of daily rain
    @FXML
    private Label windKphLabel;        // Displays wind speed
    @FXML
    private Label minTempLabel;        // Displays minimum temperature for the day
    @FXML
    private Label maxTempLabel;        // Displays maximum temperature for the day
    @FXML
    private Label statusLabel;         // Displays overall weather condition
    @FXML
    private Label cityUvLabel;         // Displays UV concentration in the city
    @FXML
    private Button backButton;         // Button to navigate back to the previous view

    /**
     * Initializes the view, clearing the detail weather label.
     */
    @FXML
    public void initialize() {
        detailWeatherLabel.setText("");
    }

    /**
     * Handles the back button click event.
     * Navigates back to the weather view if the main application reference is set.
     */
    @FXML
    private void handleBackButton(ActionEvent event) {
        if (app != null) {
            app.showWeatherView();
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    /**
     * Sets the main application reference.
     *
     * @param app the main application instance
     */
    public void setWeatherApp(HelloApplication weatherApp) {
        this.app = weatherApp;
    }

    /**
     * Updates the weather details displayed in the view.
     *
     * @param weather the weather data to display
     */
    public void setWeatherDetails(Weather weather) {
        detailWeatherLabel.setText("Detail Weather Info for " + weather.getCityName() + " On " + weather.getForecastDate());
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
