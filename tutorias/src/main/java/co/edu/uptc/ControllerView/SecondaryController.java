package co.edu.uptc.ControllerView;

import java.io.IOException;

import co.edu.uptc.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}