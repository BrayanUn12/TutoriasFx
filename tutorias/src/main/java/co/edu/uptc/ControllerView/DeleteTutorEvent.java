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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class DeleteTutorEvent implements Initializable {

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
    private TableColumn<DataTable, String> colTime;

    @FXML
    private TableColumn<DataTable, Button> colAction;

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
        eventList = FXCollections.observableArrayList();

        eventList = FXCollections.observableArrayList();
        for (Dia dia : currentTutor.getCalendarios()) {
            for (Evento evento : dia.getEventos()) {
                DataTable dataTable = new DataTable(dia.getNombre(), evento);
                dataTable.setButton(createActionButton(dataTable));
                eventList.add(dataTable);
            }
        }
        colDia.setCellValueFactory(new PropertyValueFactory<>("diaNombre"));
        colMatter.setCellValueFactory(new PropertyValueFactory<>("nombreEvento"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descripcionEvento"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("horaEvento"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));

        table.setItems(eventList);
        adjustColumnWidths();
        
    }

    private void adjustColumnWidths() {
        double columnCount = 5.0; // El número total de columnas

        colDia.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colMatter.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colDescription.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colTime.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colAction.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
    }

    private Button createActionButton(DataTable dataTable) {
        Button actionButton = new Button("Eliminar");
        actionButton.setStyle("-fx-background-color: #FF6666; -fx-border-color: #D6DBDF;");
        
        actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dia dia = currentTutor.getCalendarios().stream()
                    .filter(d -> d.getNombre().equals(dataTable.getDiaNombre()))
                    .findFirst()
                    .orElse(null);
    
                    if (dia != null) {
                        Evento evento = dataTable.getEvento();
                        boolean eventoEliminado = dia.getEventos().remove(evento);
                        System.out.println("Evento eliminado: " + eventoEliminado);
                        tutorControl.saveTutorsToJson();
                        // Actualiza la lista de la tabla y refresca la vista
                        eventList.remove(dataTable);
                        table.refresh();
                    }
            }
        });    
        return actionButton;
    }

    @FXML
    public void acceptButton() throws IOException{
        App.setRoot("tutor");
    }

}
