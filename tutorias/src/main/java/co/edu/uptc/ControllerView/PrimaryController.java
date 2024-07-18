package co.edu.uptc.ControllerView;

import java.io.IOException;

import co.edu.uptc.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
