package com.example.assignment_2gc4268.Controllers;

import com.example.assignment_2gc4268.HelloApplication;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.assignment_2gc4268.Model.Weather;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing weather data and interactions in the main weather view.
 */
public class WeatherController {

    @FXML
    private TextField cityInput;  // Input field for city name

    @FXML
    private TableView<Weather> weatherTable;  // Table to display weather data

    @FXML
    private TableColumn<Weather, String> dateColumn;  // Column for date
    @FXML
    private TableColumn<Weather, String> cityColumn;  // Column for city name
    @FXML
    private TableColumn<Weather, Double> tempColumn;  // Column for temperature
    @FXML
    private TableColumn<Weather, Double> humidityColumn;  // Column for humidity
    @FXML
    private TableColumn<Weather, Double> precipitationColumn;  // Column for precipitation

    @FXML
    private Button getWeatherInfoButton;  // Button to fetch weather info
    @FXML
    private Button getDetailedWeatherInfoButton;  // Button to view detailed weather info

    @FXML
    private Label feedbackLabel;  // Label to display feedback messages

    private ObservableList<Weather> weatherData = FXCollections.observableArrayList();  // Observable list for weather data
    private Gson gson = new Gson();  // Gson instance for JSON parsing
    private HelloApplication app;  // Reference to the main application

    /**
     * Initializes the view and sets up the table columns.
     */
    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("forecastDate"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<>("currentTemperature"));
        humidityColumn.setCellValueFactory(new PropertyValueFactory<>("currentHumidity"));
        precipitationColumn.setCellValueFactory(new PropertyValueFactory<>("currentPrecipitation"));

        weatherTable.setItems(weatherData);  // Bind data to the table
        feedbackLabel.setVisible(false);  // Hide feedback label initially
    }

    /**
     * Handles the event when the "Get Weather Info" button is clicked.
     * Fetches weather data based on the city name entered.
     */
    @FXML
    private void handleGetWeatherInfo(ActionEvent event) {
        String cityName = cityInput.getText();
        if (cityName != null && !cityName.isEmpty()) {
            List<Weather> fetchedWeatherData = fetchWeatherData(cityName);
            weatherData.clear();
            if (fetchedWeatherData.isEmpty()) {
                showFeedback("No weather data available for the entered city.");
            } else {
                weatherData.addAll(fetchedWeatherData);
                hideFeedback();  // Hide feedback if data is available
            }
        } else {
            showFeedback("Please enter a city name.");
        }
    }

    /**
     * Fetches weather data from the API for the given city name.
     *
     * @param cityName the name of the city
     * @return a list of weather data
     */
    private List<Weather> fetchWeatherData(String cityName) {
        String apiKey = "022136477bd44954982185357243007";
        String apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + cityName + "&days=10";
        List<Weather> weatherList = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader reader = new InputStreamReader(conn.getInputStream());
                JsonObject dataObj = JsonParser.parseReader(reader).getAsJsonObject();
                JsonObject forecastObj = dataObj.getAsJsonObject("forecast");
                JsonArray forecastDaysArray = forecastObj.getAsJsonArray("forecastday");

                for (int i = 0; i < forecastDaysArray.size(); i++) {
                    JsonObject dayObj = forecastDaysArray.get(i).getAsJsonObject();
                    String date = dayObj.get("date").getAsString();
                    JsonObject dayDetails = dayObj.getAsJsonObject("day");

                    // Extract weather details
                    double temperature = dayDetails.has("avgtemp_c") ? dayDetails.get("avgtemp_c").getAsDouble() : 0.0;
                    double humidity = dayDetails.has("avghumidity") ? dayDetails.get("avghumidity").getAsDouble() : 0.0;
                    double precipitation = dayDetails.has("totalprecip_mm") ? dayDetails.get("totalprecip_mm").getAsDouble() : 0.0;
                    double windKPH = dayDetails.has("maxwind_kph") ? dayDetails.get("maxwind_kph").getAsDouble() : 0.0;
                    double minTemp = dayDetails.has("mintemp_c") ? dayDetails.get("mintemp_c").getAsDouble() : 0.0;
                    double maxTemp = dayDetails.has("maxtemp_c") ? dayDetails.get("maxtemp_c").getAsDouble() : 0.0;
                    int dailyRain = dayDetails.has("daily_chance_of_rain") ? dayDetails.get("daily_chance_of_rain").getAsInt() : 0;
                    String condition = dayDetails.has("condition") ? dayDetails.getAsJsonObject("condition").get("text").getAsString() : "";
                    int cityUv = dayDetails.has("uv") ? dayDetails.get("uv").getAsInt() : 0;

                    Weather weather = new Weather(cityName, temperature, humidity, precipitation, date, windKPH, minTemp, maxTemp, dailyRain, condition, cityUv);
                    weatherList.add(weather);
                }
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                showFeedback("No weather data available for this city. Please check the city name.");
            } else {
                showFeedback("An error occurred while fetching weather data.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showFeedback("An error occurred while fetching weather data.");
        }

        return weatherList;
    }

    /**
     * Shows detailed weather info for the selected item in the table.
     */
    @FXML
    private void weatherDetailsByDate(ActionEvent event) {
        Weather selectedWeather = weatherTable.getSelectionModel().getSelectedItem();
        if (selectedWeather != null) {
            app.showDetailView(selectedWeather);  // Show detailed weather view
        } else {
            showFeedback("No weather data selected.");
        }
    }

    /**
     * Displays a feedback message to the user.
     *
     * @param message the feedback message
     */
    private void showFeedback(String message) {
        feedbackLabel.setText(message);
        feedbackLabel.setVisible(true);
    }

    /**
     * Hides the feedback message.
     */
    private void hideFeedback() {
        feedbackLabel.setVisible(false);
    }

    /**
     * Sets the reference to the main application.
     *
     * @param weatherApp the main application instance
     */
    public void setWeatherApp(HelloApplication weatherApp) {
        this.app = weatherApp;
    }
}
