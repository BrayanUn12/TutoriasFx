module co.edu.uptc {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens co.edu.uptc to javafx.fxml;
    opens co.edu.uptc.controller to com.google.gson;
    opens co.edu.uptc.model to com.google.gson;


    exports co.edu.uptc;
    exports co.edu.uptc.controller;
    exports co.edu.uptc.model;
    exports co.edu.uptc.ControllerView;
    opens co.edu.uptc.ControllerView to javafx.fxml;

}
