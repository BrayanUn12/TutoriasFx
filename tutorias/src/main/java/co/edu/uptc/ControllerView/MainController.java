package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {
    @FXML
    public void switchToStudent() throws IOException {
        App.setRoot("student");
    }

    @FXML
    public void switchToTutor() throws IOException {
        App.setRoot("tutor");
    }
}
