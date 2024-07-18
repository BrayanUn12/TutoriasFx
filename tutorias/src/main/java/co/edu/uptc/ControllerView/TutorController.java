package co.edu.uptc.ControllerView;

import java.util.ArrayList;

import co.edu.uptc.App;
import co.edu.uptc.model.Tutor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.google.gson.reflect.TypeToken;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
