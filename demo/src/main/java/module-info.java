module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    exports co.edu.uptc.app;
    exports co.edu.uptc.controller;
    exports co.edu.uptc.model;
    exports co.edu.uptc.util;
    
    opens com.example to javafx.fxml;
    exports com.example;


}
