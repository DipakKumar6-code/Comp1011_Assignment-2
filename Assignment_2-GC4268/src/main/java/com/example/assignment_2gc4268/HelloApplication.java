package com.example.assignment_2gc4268;

import com.example.assignment_2gc4268.Controllers.DetailWeatherInfoController;
import com.example.assignment_2gc4268.Controllers.WeatherController;
import com.example.assignment_2gc4268.Model.Weather;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
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

        // Load DetailView.fxml for future use
        FXMLLoader detailLoader = new FXMLLoader(getClass().getResource("MoreWeatherView.fxml"));
        AnchorPane detailView = detailLoader.load();
        detailScene = new Scene(detailView);

        // Set initial scene to WeatherView
        primaryStage.setScene(weatherScene);
        primaryStage.show();

        // Optionally: Pass the Scene or Stage to controllers if needed
        WeatherController weatherController = weatherLoader.getController();
        DetailWeatherInfoController detailedController = detailLoader.getController();
        weatherController.setDetailController(detailedController);
    }

    public void showWeatherView() {
        primaryStage.setScene(weatherScene);
    }

    public void showDetailView(Weather weather) {
        DetailWeatherInfoController detailedController = (DetailWeatherInfoController) ((FXMLLoader) detailScene.getRoot().getProperties().get("controller")).getController();
        detailedController.setWeatherDetails(weather);
        primaryStage.setScene(detailScene);
    }

    public static void main(String[] args) {
        launch();
    }
}