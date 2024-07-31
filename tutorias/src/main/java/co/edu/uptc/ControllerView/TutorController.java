package co.edu.uptc.ControllerView;

  

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.controller.TutorControl;
import co.edu.uptc.model.Estudent;
import co.edu.uptc.model.Tutor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class TutorController implements Initializable{


    @FXML
    private Label labelName;

    @FXML
    private TextField nombreField, descripcionField, horaInicioField, horaFinalField, diaField, nombreFieldDelete,nombreBuscarField, nombreFieldEdit, descripcionFieldEdit, horaInicioFieldEdit, horaFinalFieldEdit;

    @FXML
    private CheckBox inscritoCheckBox;


    private Tutor currentTutor;
    private TutorControl tutorControl;
    private Evento eventoact;

//    private void initializeLabel() {
//        InteractionClass<Tutor> interactionInstance = InteractionClass.getInstance();
//        Tutor tutor = interactionInstance.getObject();
//        labelName.setText(tutor.getFirstName() + " " + tutor.getLastName());
//
//    }
    @Override
    public void initialize (URL location, ResourceBundle resources) {
//        initializeLabel();

        InteractionClass<Tutor> interactionInstance = InteractionClass.getInstance();

        currentTutor = interactionInstance.getObject();
        tutorControl = new TutorControl();
    }



    @FXML
    public void switchTutor() throws IOException {
    App.setRoot("tutor");

    }



    @FXML
    public void volverMenuPrincipal() throws IOException{
    App.setRoot("main");
    }



    @FXML
    public void handleGuardarTutoria() {

    try {

      String nombre = nombreField.getText();

      String descripcion = descripcionField.getText();

      String dayName= diaField.getText();

      LocalTime horaInicio = LocalTime.parse(horaInicioField.getText());

      LocalTime horaFinal = LocalTime.parse(horaFinalField.getText());

      boolean inscrito = inscritoCheckBox.isSelected();



      Evento evento = new Evento(nombre, descripcion, horaInicio, horaFinal, inscrito);



      List<Dia> calendarios= currentTutor.getCalendarios();



      Dia dia = calendarios.stream()

          .filter(d -> d.getNombre().equalsIgnoreCase(dayName))

          .findFirst()

          .orElse(new Dia(dayName));



      if (dia.addEvento(evento)) {

        if (!calendarios.contains(dia)) {

          calendarios.add(dia);

        }

      for (Tutor tutor : tutorControl.getTutors()) {

        if (tutor.getId()==currentTutor.getId()) {

            tutor.setCalendarios(calendarios);

            break;

        }

      }

        tutorControl.saveTutorsToJson();

        showAlert(Alert.AlertType.INFORMATION, "Éxito", "La tutoría ha sido guardada exitosamente.");

      }else

      showAlert(Alert.AlertType.ERROR, "Error", "Hubo un problema al guardar la tutoría. Por favor, verifica los datos ingresados.");

      }catch(Exception e){

        showAlert(Alert.AlertType.ERROR, "Error", "Hubo un problema al guardar la tutoría. Por favor, verifica los datos ingresados.");

        e.printStackTrace();

      }



    }



    private void showAlert(AlertType alertType, String title, String message) {

    Alert alert = new Alert(alertType);

    alert.setTitle(title);

    alert.setHeaderText(null);

    alert.setContentText(message);

    alert.showAndWait();

    }



    @FXML
    public void volverMenu() throws IOException{

    App.setRoot("tutor");

    }




    @FXML
    public void switchToAddTutoring() throws IOException {

    App.setRoot("addEventTutor");

    }





    @FXML
    public void switchToShow() throws IOException {

    App.setRoot("showInfoTutor");

    }



    //

    @FXML
    public void switchToDElete () throws IOException {

    App.setRoot("deleteInfoTutor");

    }



    @FXML
    public void handleBorrarTutoria (){

    try {

      String nombreDelete= nombreFieldDelete.getText();

      List<Dia> calendarios= currentTutor.getCalendarios();

      boolean foundEvent=false;

      for (Dia dia : calendarios) {

        for (Evento evento: dia.getEventos()) {

          if (evento.getNombre().equalsIgnoreCase(nombreDelete)) {

            dia.getEventos().remove(evento);

            foundEvent=true;

            break;

          }

        }

        if (foundEvent) break;

      }

      if (foundEvent) {

        for (Tutor tutor: tutorControl.getTutors()) {

          if (tutor.getId() == currentTutor.getId()) {

            tutor.setCalendarios(calendarios);

            break;

          }

        }

        tutorControl.saveTutorsToJson();

        showAlert(Alert.AlertType.INFORMATION, "Borrado ", "exitosamente");

      }else {

        showAlert(Alert.AlertType.ERROR, "Error", "No se encontró una tutoría con el nombre especificado.");

      }




    } catch (Exception e ) {

      showAlert(Alert.AlertType.ERROR, "Error", "Hubo un problema al borrar la tutoría. Por favor, verifica los datos ingresados.");

      e.printStackTrace();

    }

    }



    @FXML
    public void switchToMain () throws IOException{

    App.setRoot("tutor");

    }






    @FXML
    public void switchToUpdate () throws IOException {

    App.setRoot("editTutor");

    }



    @FXML
    public void handleBuscarTutoria() throws IOException{

    String nombreBuscar = nombreBuscarField.getText();

    List<Dia> calendarios = currentTutor.getCalendarios();



    for (Dia dia : calendarios) {

      for (Evento evento : dia.getEventos()) {

        if (evento.getNombre().equalsIgnoreCase(nombreBuscar)) {

          eventoact = evento;

          nombreFieldEdit.setText(evento.getNombre());

          descripcionFieldEdit.setText(evento.getDescripcion());

          horaInicioFieldEdit.setText(evento.getHoraInicio().toString());

          horaFinalFieldEdit.setText(evento.getHoraFinal().toString());

          return;

        }

      }

    }



    showAlert(Alert.AlertType.ERROR, "Error", "No se encontró una tutoría con el nombre especificado.");

    }




    @FXML
    public void handleGuardarTutoriaEditada() {

    if (eventoact == null) {

      showAlert(Alert.AlertType.ERROR, "Error", "Primero busca una tutoría para editar.");

      return;

    }



    try {

      eventoact.setNombre(nombreFieldEdit.getText());

      eventoact.setDescripcion(descripcionFieldEdit.getText());

      eventoact.setHoraInicio(LocalTime.parse(horaInicioFieldEdit.getText()));

      eventoact.setHoraFinal(LocalTime.parse(horaFinalFieldEdit.getText()));



      for (Tutor tutor : tutorControl.getTutors()) {

        if (tutor.getId() == currentTutor.getId()) {

          tutor.setCalendarios(currentTutor.getCalendarios());

          break;

        }

      }
      tutorControl.saveTutorsToJson();
      showAlert(Alert.AlertType.INFORMATION, "Éxito", "La tutoría ha sido editada exitosamente.");
    } catch (Exception e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Hubo un problema al editar la tutoría. Por favor, verifica los datos ingresados.");
      e.printStackTrace();
    }
    }



    @FXML
    public void switchToMainWindow () throws IOException{
    App.setRoot("tutor");
    }

    }