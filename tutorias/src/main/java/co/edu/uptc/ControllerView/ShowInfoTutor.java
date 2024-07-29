package co.edu.uptc.ControllerView;

  

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

  

import co.edu.uptc.App;

import co.edu.uptc.controller.Dia;

import co.edu.uptc.controller.Evento;

import co.edu.uptc.model.Tutor;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.geometry.Pos;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

  

public class ShowInfoTutor implements Initializable {

  

 @FXML

 private VBox showTutorInfo;

  

 @FXML

 private Button acceptButton ;

  

  public ShowInfoTutor() {

 }

  

 @Override

 public void initialize(URL location, ResourceBundle resources){

  

 Tutor tutor = InteractionClass.getInstance().getTutor();

 Label label = new Label("Informacion");
 
 for (Dia dia : tutor.getCalendarios()) {

      VBox eventosBox = new VBox();

      eventosBox.setAlignment(Pos.CENTER);

      eventosBox.setSpacing(10);


      for (Evento evento: dia.getEventos()) {

        label = new Label("Tutoria "  + " "

        + evento.getNombre() + " " + evento.getHoraInicioFormatted() + " "

        + evento.getHoraFinalFormatted());

      }

      eventosBox.getChildren().addAll(label);

      showTutorInfo.getChildren().add(eventosBox);

      acceptButton.setAlignment(Pos.BOTTOM_CENTER);

      eventosBox.getChildren().add(acceptButton);

    }

  }

  

  public void acceptButton() throws IOException {

    App.setRoot("tutor");

  }

  

}