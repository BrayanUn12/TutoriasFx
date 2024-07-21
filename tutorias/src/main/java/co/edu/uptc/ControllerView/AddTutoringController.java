package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.model.Estudent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Scene; //Permite mostrar una ventana emergente, contenedor donde se muestran todos los nodos utilizado en el proyecto


public class AddTutoringController implements Initializable {

    private StudentControl studentControl;

    private ArrayList<Estudent> students;

    private StudentControl secondaryController;

//    @FXML
//    private Label tutoring;

    @FXML
    private Label tutoring2;

    @FXML
    private VBox tutoring; // Asegúrate de que 'tutoring' esté definido como VBox en tu archivo FXML

    public void switchAddTutoring () throws IOException {
        App.setRoot("addTutoring");
    }

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
            Label label = new Label("Hello; \n" + " " + dia.showEvents());
            Button button = new Button("Añadir Tutoria");

            // Añade un manejador de eventos al botón

            // Añade un manejador de eventos al botón usando una clase anónima
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Itera sobre los eventos y marca como inscritos los que no lo están
                    for (Evento evento : dia.getEventos()) {
                        if (!evento.isInscrito()) {
                            evento.setInscrito(true);

                        }
                    }
                    // Imprime el mensaje en la consola
                    try {
                        switchtutoringAdd();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

            eventBox.getChildren().addAll(label, button);
            tutoring.getChildren().add(eventBox);
        }
    }
    public void switchToBack() throws IOException {
        App.setRoot("menuStudent");
    }

    private void switchtutoringAdd() throws IOException {
        App.setRoot("tutoringAdd");
    }
}
