package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.model.Estudent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowTutoringController implements Initializable {
    @FXML
    private VBox show; // Asegúrate de que 'tutoring' esté definido como VBox en tu archivo FXML

    @FXML
    private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Estudent student = InteractionClass.getInstance().getStudent();
        Label label = new Label("Tutoring");
        for (Dia dia : student.getCalendarios()) {
            VBox eventBox = new VBox();
            eventBox.setAlignment(Pos.CENTER);
            eventBox.setSpacing(10);
            int count = 1;
            for (Evento evento : dia.getEventos()) {
                if (evento.isInscrito() == true) {
                    label = new Label("Tutoria " + count++ + " " + " "
                            + evento.getNombre() + " "
                            + evento.getHoraInicio() + " - "
                            + evento.getHoraFinal() );
                }
            }


            eventBox.getChildren().addAll(label);
            show.getChildren().add(eventBox);
            button.setAlignment(Pos.BOTTOM_CENTER);
            eventBox.getChildren().add(button);
        }
    }
    public void switchToBack() throws IOException {
        App.setRoot("menuStudent");
    }

}
