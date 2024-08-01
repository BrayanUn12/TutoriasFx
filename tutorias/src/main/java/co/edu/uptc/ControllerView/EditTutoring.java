package co.edu.uptc.ControllerView;

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

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class EditTutoring implements Initializable {

    @FXML
    private Label labelName;

    @FXML
    private TableView<DataTable> table;

    @FXML
    private TableColumn<DataTable, String> colDia;

    @FXML
    private TableColumn<DataTable, String> colMatter;

    @FXML
    private TableColumn<DataTable, String> colDescription;

    @FXML
    private TableColumn<DataTable, String> colTimeInit;

    @FXML
    private TableColumn<DataTable, String> colTimeFinal;

    @FXML
    private TableColumn<DataTable, Button> colAction;

    @FXML
    private TextField nombreFieldEdit, descripcionFieldEdit, horaInicioFieldEdit, horaFinalFieldEdit;

    private ObservableList<DataTable> eventList;

    private TutorControl tutorControl;

    private DataTable selectedDataTable;


    private void initializeLabel() {
        InteractionClass<Tutor> interactionInstance = InteractionClass.getInstance();
        Tutor tutor = interactionInstance.getObject();
        labelName.setText(tutor.getFirstName() + " " + tutor.getLastName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeLabel();
        InteractionClass<Tutor> interactionInstance = InteractionClass.getInstance();
        Tutor tutor = interactionInstance.getObject();
        tutorControl = new TutorControl(); // Asegúrate de inicializar tutorControl aquí

        eventList = FXCollections.observableArrayList();
        if (tutor != null) {
            for (Dia dia : tutor.getCalendarios()) {
                for (Evento evento : dia.getEventos()) {
                    DataTable dataTable = new DataTable(dia.getNombre(), evento);
                    dataTable.setButton(createActionButton(dataTable));
                    eventList.add(dataTable);
                }
            }
        }

        colDia.setCellValueFactory(new PropertyValueFactory<>("diaNombre"));
        colMatter.setCellValueFactory(new PropertyValueFactory<>("nombreEvento"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descripcionEvento"));
        colTimeInit.setCellValueFactory(new PropertyValueFactory<>("horaInicialEvento"));
        colTimeFinal.setCellValueFactory(new PropertyValueFactory<>("horaFinalEvento"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));

        table.setItems(eventList);

        adjustColumnWidths();
    }

    private Button createActionButton(DataTable dataTable) {
        Button actionButton = new Button("Editar");
        actionButton.setStyle("-fx-background-color: #66CCCC; -fx-border-color: #D6DBDF;");

        actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleBuscarTutoria(dataTable);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return actionButton;
    }

    private void adjustColumnWidths() {
        double columnCount = 5.0; // El número total de columnas

        colDia.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colMatter.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colDescription.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colTimeInit.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colTimeFinal.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colAction.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
    }

    @FXML
    public void handleBuscarTutoria(DataTable selectedData) throws IOException {
        // Guarda la referencia al evento seleccionado
        this.selectedDataTable = selectedData;

        // Establece los valores en los campos de texto

        nombreFieldEdit.setText(selectedData.getNombreEvento());
        descripcionFieldEdit.setText(selectedData.getDescripcionEvento());
        horaInicioFieldEdit.setText(selectedData.getHoraInicialEvento());
        horaFinalFieldEdit.setText(selectedData.getHoraFinalEvento());
    }

    @FXML
    public void handleGuardarTutoriaEditada() {

        InteractionClass<Tutor> interactionInstance = InteractionClass.getInstance();
        Tutor tutorT = interactionInstance.getObject();

        if (selectedDataTable != null) {
            // Obtiene el evento seleccionado
            Evento evento = selectedDataTable.getEvento();

            // Actualiza los detalles del evento
            evento.setNombre(nombreFieldEdit.getText());
            evento.setDescripcion(descripcionFieldEdit.getText());
            evento.setHoraInicio(LocalTime.parse(horaInicioFieldEdit.getText()));
            evento.setHoraFinal(LocalTime.parse(horaFinalFieldEdit.getText()));

            for (Tutor tutor : tutorControl.getTutors()) {

                if (tutor.getId() == tutorT.getId()) {

                    tutor.setCalendarios(tutorT.getCalendarios());
                    nombreFieldEdit.setText(null);
                    descripcionFieldEdit.setText(null);
                    horaInicioFieldEdit.setText(null);
                    horaFinalFieldEdit.setText(null);

                    eventList = FXCollections.observableArrayList();
                    if (tutor != null) {
                        for (Dia dia : tutor.getCalendarios()) {
                            for (Evento evento2 : dia.getEventos()) {
                                DataTable dataTable = new DataTable(dia.getNombre(), evento2);
                                dataTable.setButton(createActionButton(dataTable));
                                eventList.add(dataTable);
                            }
                        }
                    }

                    colDia.setCellValueFactory(new PropertyValueFactory<>("diaNombre"));
                    colMatter.setCellValueFactory(new PropertyValueFactory<>("nombreEvento"));
                    colDescription.setCellValueFactory(new PropertyValueFactory<>("descripcionEvento"));
                    colTimeInit.setCellValueFactory(new PropertyValueFactory<>("horaInicialEvento"));
                    colTimeFinal.setCellValueFactory(new PropertyValueFactory<>("horaFinalEvento"));
                    colAction.setCellValueFactory(new PropertyValueFactory<>("button"));

                    table.setItems(eventList);

                    adjustColumnWidths();

                    break;

                }

            }
            // Guarda los cambios

            tutorControl.saveTutorsToJson();

            // Muestra un mensaje de éxito
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "La tutoría ha sido guardada exitosamente.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "No se ha seleccionado ninguna tutoría para editar.");
        }
    }


    @FXML
    public void switchToMain() throws IOException {
        App.setRoot("main");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();

    }
}
