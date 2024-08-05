module com.example.assignment_2gc4268 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.assignment_2gc4268.Controllers to javafx.fxml, com.google.gson;
    opens com.example.assignment_2gc4268.Model to javafx.base;
    exports com.example.assignment_2gc4268;
}