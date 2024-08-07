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
import com.example.assignment_2gc4268.Model.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherController {

    @FXML
    private TextField cityInput;

    @FXML
    private TableView<Weather> weatherTable;

    @FXML
    private TableColumn<Weather, String> dateColumn;

    @FXML
    private TableColumn<Weather, String> cityColumn;

    @FXML
    private TableColumn<Weather, Double> tempColumn;

    @FXML
    private TableColumn<Weather, Double> humidityColumn;

    @FXML
    private TableColumn<Weather, Double> precipitationColumn;

    @FXML
    private Button getWeatherInfoButton;

    @FXML
    private Button getDetailedWeatherInfoButton;

    @FXML
    private Label feedbackLabel;

    private ObservableList<Weather> weatherData = FXCollections.observableArrayList();
    private Gson gson = new Gson();
    private HelloApplication app;

    @FXML
    public void initialize() {
        // Initialize columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("forecastDate"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<>("currentTemperature"));
        humidityColumn.setCellValueFactory(new PropertyValueFactory<>("currentHumidity"));
        precipitationColumn.setCellValueFactory(new PropertyValueFactory<>("currentPrecipitation"));

        // Bind the weather data to the table
        weatherTable.setItems(weatherData);

        // Hide the feedback label initially
        feedbackLabel.setVisible(false);
    }

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
                hideFeedback(); // Hide feedback if data is available
            }
        } else {
            showFeedback("Please enter a city name.");
        }
    }

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

                    double temperature = dayDetails.has("avgtemp_c") && !dayDetails.get("avgtemp_c").isJsonNull()
                            ? dayDetails.get("avgtemp_c").getAsDouble() : 0.0;
                    double humidity = dayDetails.has("avghumidity") && !dayDetails.get("avghumidity").isJsonNull()
                            ? dayDetails.get("avghumidity").getAsDouble() : 0.0;
                    double precipitation = dayDetails.has("totalprecip_mm") && !dayDetails.get("totalprecip_mm").isJsonNull()
                            ? dayDetails.get("totalprecip_mm").getAsDouble() : 0.0;
                    double windKPH = dayDetails.has("maxwind_kph") && !dayDetails.get("maxwind_kph").isJsonNull()
                            ? dayDetails.get("maxwind_kph").getAsDouble() : 0.0;
                    double minTemp = dayDetails.has("mintemp_c") && !dayDetails.get("mintemp_c").isJsonNull()
                            ? dayDetails.get("mintemp_c").getAsDouble() : 0.0;
                    double maxTemp = dayDetails.has("maxtemp_c") && !dayDetails.get("maxtemp_c").isJsonNull()
                            ? dayDetails.get("maxtemp_c").getAsDouble() : 0.0;
                    int dailyRain = dayDetails.has("daily_chance_of_rain") && !dayDetails.get("daily_chance_of_rain").isJsonNull()
                            ? dayDetails.get("daily_chance_of_rain").getAsInt() : 0;
                    String condition = dayDetails.has("condition") && dayDetails.getAsJsonObject("condition").has("text")
                            ? dayDetails.getAsJsonObject("condition").get("text").getAsString() : "";
                    int cityUv = dayDetails.has("uv") && !dayDetails.get("uv").isJsonNull()
                            ? dayDetails.get("uv").getAsInt() : 0;

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

    @FXML
    private void weatherDetailsByDate(ActionEvent event) {
        Weather selectedWeather = weatherTable.getSelectionModel().getSelectedItem();
        if (selectedWeather != null) {
            app.showDetailView(selectedWeather);
        } else {
            showFeedback("No weather data selected.");
        }
    }

    private void showFeedback(String message) {
        feedbackLabel.setText(message);
        feedbackLabel.setVisible(true);
    }

    private void hideFeedback() {
        feedbackLabel.setVisible(false);
    }

    public void setApp(HelloApplication app) {
        this.app = app;
    }
}
