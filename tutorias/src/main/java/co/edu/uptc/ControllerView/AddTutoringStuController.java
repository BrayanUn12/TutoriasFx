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

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;


public class AddTutoringStuController implements Initializable {

    @FXML
    private VBox tutoring;

    @FXML
    private Label labelName;

    @FXML
    private Label labelCode;

    public void switchAddTutoring () throws IOException {
        App.setRoot("addTutoringStudent");
    }

    public void initializeLabel() {
        Estudent student = InteractionClass.getInstance().getStudent();
        labelName.setText(student.getFirstName() + " " + student.getLastName());
        String code = String.valueOf(student.getCodigo());
        labelCode.setText(code);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Estudent student = InteractionClass.getInstance().getStudent();
        initializeLabel();

        for (Dia dia : student.getCalendarios()) {
            HBox eventBox = new HBox();
            eventBox.setAlignment(Pos.CENTER);

            Label state = new Label();
            if (dia.getEventos().get(0).isInscrito() == false){
                state.setText("No agendada");
            }else {
                state.setText("Agendada");
            }


        Label label = new Label(dia.getNombre() + "\t" + dia.getEventos().get(0).getNombre() +
                    "\t  Tema: " + dia.getEventos().get(0).getDescripcion() + "\t " + dia.getEventos().get(0).getHoraInicio() +
                    "-" + dia.getEventos().get(0).getHoraFinal() + "\t " + state.getText());

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
        App.setRoot("tutoringAddStudent");
    }
}
