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

public class DeleteTutoringController implements Initializable {

    @FXML
    private VBox delete; // Asegúrate de que 'tutoring' esté definido como VBox en tu archivo FXML

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        Estudent student = InteractionClass.getInstance().getStudent();
//        StringBuilder eventsText = new StringBuilder();
//
//        for (Dia dia : student.getCalendarios()) {
//            eventsText.append("Hello; \n").append(" ").append(dia.showEvents()).append("\n");
//        }
//        tutoring.setText(eventsText.toString());
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Estudent student = InteractionClass.getInstance().getStudent();

        for (Dia dia : student.getCalendarios()) {
            HBox eventBox = new HBox();
            eventBox.setAlignment(Pos.CENTER);
            Label label = new Label("Hello; \n" + " " + dia.showEvents());
            Button button = new Button("Eliminar Tutoria");

            // Añade un manejador de eventos al botón usando una clase anónima
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Itera sobre los eventos y marca como inscritos los que no lo están
                    for (Evento evento : dia.getEventos()) {
                        if (evento.isInscrito() == true) {
                            evento.setInscrito(false);
                        }
                    }
                    // Imprime el mensaje en la consola
                    try {
                        switchtutoringDelete();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

            eventBox.getChildren().addAll(label, button);
            delete.getChildren().add(eventBox);
        }
    }
    public void switchToBack() throws IOException {
        App.setRoot("menuStudent");
    }

    private void switchtutoringDelete() throws IOException {
        App.setRoot("tutoringDelete");
    }
}
