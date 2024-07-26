package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class TutorController {

    @FXML
    public void switchTutor() throws IOException {
       App.setRoot("tutor");
    }

    @FXML
    public void switchToAddTutoring() throws IOException {
        App.setRoot("addEventTutor");
    }

    @FXML
    public void switchToShow() throws IOException {
        App.setRoot("main");
    }
    @FXML
    public void switchToDElete () throws IOException {
        App.setRoot("main");
    }
    @FXML
    public void switchToUpdate () throws IOException {
        App.setRoot("main");
    }
}
