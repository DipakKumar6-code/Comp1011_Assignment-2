package com.example.assignment_2gc4268;

import com.example.assignment_2gc4268.Model.Weather;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    Weather weather = new Weather();
}