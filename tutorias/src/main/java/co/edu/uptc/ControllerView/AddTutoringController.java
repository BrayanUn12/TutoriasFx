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
import javafx.geometry.Pos;
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

    @FXML
    private VBox tutoring;

    public void switchAddTutoring () throws IOException {
        App.setRoot("addTutoring");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Estudent student = InteractionClass.getInstance().getStudent();

        for (Dia dia : student.getCalendarios()) {
            HBox eventBox = new HBox();
            eventBox.setAlignment(Pos.CENTER);

            Label state = new Label();
            if (dia.getEventos().get(0).isInscrito() == false){
                state.setText("No agendada");
            }else {
                state.setText("Agendada");
            }

            Label label = new Label(dia.getNombre() + "\n" + dia.getEventos().get(0).getNombre() +
                    "\nTema: " + dia.getEventos().get(0).getDescripcion() + "\n " + dia.getEventos().get(0).getHoraInicio() +
                    "-" + dia.getEventos().get(0).getHoraFinal() + "\n" + state.getText());

            Button button = new Button("Añadir Tutoria");

            // Añade un manejador de eventos al botón usando una clase anónima
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Itera sobre los eventos y marca como inscritos los que no lo están
                    for (Evento evento : dia.getEventos()) {
                        if (!evento.isInscrito()) {
                            evento.setInscrito(true);
                        }else if (evento.isInscrito() == true){
                            evento.isInscrito();
                            System.out.println("Tutoria ya inscrita");
                        }
                    }
                    // Imprime el mensaje en la consola
                    try {
                        switchTutoringAdd();
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

    private void switchTutoringAdd() throws IOException {
        App.setRoot("tutoringAdd");
    }
}
