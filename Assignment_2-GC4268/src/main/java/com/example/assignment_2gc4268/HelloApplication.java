package com.example.assignment_2gc4268;

import com.example.assignment_2gc4268.Controllers.DetailWeatherInfoController;
import com.example.assignment_2gc4268.Controllers.WeatherController;
import com.example.assignment_2gc4268.Model.Weather;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private Scene weatherScene;
    private Scene detailScene;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        primaryStage.setTitle("Weather Application");

        // Load WeatherView.fxml and set it as the initial scene
        FXMLLoader weatherLoader = new FXMLLoader(getClass().getResource("WeatherView.fxml"));
        AnchorPane weatherView = weatherLoader.load();
        weatherScene = new Scene(weatherView);

        // Load DetailView.fxml but do not set it initially
        FXMLLoader detailLoader = new FXMLLoader(getClass().getResource("MoreWeatherView.fxml"));
        AnchorPane detailView = detailLoader.load();
        detailScene = new Scene(detailView);

        // Set initial scene to WeatherView
        primaryStage.setScene(weatherScene);
        primaryStage.show();

        // Initialize controllers
        WeatherController weatherController = weatherLoader.getController();
        DetailWeatherInfoController detailController = detailLoader.getController();

        // Pass the app instance to controllers
        weatherController.setWeatherApp(this);
        detailController.setWeatherApp(this);
    }

    public void showWeatherView() {
        primaryStage.setScene(weatherScene);
    }

    public void showDetailView(Weather weather) {
        try {
            FXMLLoader detailLoader = new FXMLLoader(getClass().getResource("MoreWeatherView.fxml"));
            AnchorPane detailView = detailLoader.load();
            DetailWeatherInfoController detailController = detailLoader.getController();
            detailController.setWeatherDetails(weather);
            detailController.setWeatherApp(this);
            Scene detailScene = new Scene(detailView);
            primaryStage.setScene(detailScene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

