package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {

    /**
     * Metodo que retorna de la ventana principal a la ventana de Estudiante
     * @throws IOException
     */
    @FXML
    public void switchToStudent() throws IOException {
        App.setRoot("student");
    }

    /**
     * Metodo que retorna de la ventana principal a la ventana de Tutor
     * @throws IOException
     */
    @FXML
    public void switchToTutor() throws IOException {
        App.setRoot("tutor");
    }
}
