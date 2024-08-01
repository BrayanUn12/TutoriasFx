package co.edu.uptc.ControllerView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.controller.TutorControl;
import co.edu.uptc.model.DataTable;
import co.edu.uptc.model.Tutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class DeleteTutorEvent implements Initializable {

    @FXML
    private Label labelName;

    @FXML
    private TextField nombreFieldDelete;

    @FXML
    private TableView<DataTable> table;

    @FXML
    private TableColumn<DataTable, String> colDia;

    @FXML
    private TableColumn<DataTable, String> colMatter;

    @FXML
    private TableColumn<DataTable, String> colDescription;

    @FXML
    private TableColumn<DataTable, String> colTime;


    private ObservableList<DataTable> eventList;


    private Tutor currentTutor;
    private TutorControl tutorControl;
    private Evento eventoact;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InteractionClass<Tutor> interactionInstance = InteractionClass.getInstance();
        currentTutor = interactionInstance.getObject();
        tutorControl = new TutorControl();
        labelName.setText(currentTutor.getFirstName() + " " + currentTutor.getLastName());

        setTextSize(nombreFieldDelete, 200,30);

        eventList = FXCollections.observableArrayList();
        for (Dia dia : currentTutor.getCalendarios()) {
            for (Evento evento : dia.getEventos()) {
                DataTable dataTable = new DataTable(dia.getNombre(), evento);
                eventList.add(dataTable);
            }
        }
        colDia.setCellValueFactory(new PropertyValueFactory<>("diaNombre"));
        colMatter.setCellValueFactory(new PropertyValueFactory<>("nombreEvento"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descripcionEvento"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("horaEvento"));

        table.setItems(eventList);
        adjustColumnWidths();

    }

    private void adjustColumnWidths() {
        double columnCount = 4.0; // El número total de columnas

        colDia.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colMatter.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colDescription.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colTime.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
    }


    @FXML
    public void acceptButton() throws IOException{
        App.setRoot("tutor");
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
                        eventList = FXCollections.observableArrayList();
                        for (Dia dia : currentTutor.getCalendarios()) {
                            for (Evento evento : dia.getEventos()) {
                                DataTable dataTable = new DataTable(dia.getNombre(), evento);
                                eventList.add(dataTable);
                            }
                        }
                        colDia.setCellValueFactory(new PropertyValueFactory<>("diaNombre"));
                        colMatter.setCellValueFactory(new PropertyValueFactory<>("nombreEvento"));
                        colDescription.setCellValueFactory(new PropertyValueFactory<>("descripcionEvento"));
                        colTime.setCellValueFactory(new PropertyValueFactory<>("horaEvento"));

                        table.setItems(eventList);
                        adjustColumnWidths();
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void switchToMain() throws IOException {
        App.setRoot("tutor");
    }

    private void setTextSize(TextField textField, double width, double height) {
        textField.setPrefWidth(width);
        textField.setPrefHeight(height);
        textField.setMinWidth(width);
        textField.setMinHeight(height);
        textField.setMaxWidth(width);
        textField.setMaxHeight(height);
    }

}