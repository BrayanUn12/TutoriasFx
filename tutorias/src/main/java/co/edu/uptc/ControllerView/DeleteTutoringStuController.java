package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.model.DataTable;
import co.edu.uptc.model.Estudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteTutoringStuController implements Initializable {

    @FXML
    private Label labelName;

    @FXML
    private Label labelCode;

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

    public void switchDeleteTutoring() throws IOException {
        App.setRoot("deleteTutoringStudent");
    }

    public void initializeLabel() {
        InteractionClass<Estudent> interactionInstance = InteractionClass.getInstance();
        Estudent student = interactionInstance.getObject();
        labelName.setText(student.getFirstName() + " " + student.getLastName());
        String code = String.valueOf(student.getCodigo());
        labelCode.setText(code);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InteractionClass<Estudent> interactionInstance = InteractionClass.getInstance();
        Estudent student = interactionInstance.getObject();
        initializeLabel();

        eventList = FXCollections.observableArrayList();
        for (Dia dia : student.getCalendarios()) {
            for (Evento evento : dia.getEventos()) {
                if (evento.isInscrito()) {
                    DataTable dataTable = new DataTable(dia.getNombre(), evento);
                    dataTable.setButton(createActionButton(dataTable)); // Añade el botón con el EventHandler
                    eventList.add(dataTable);
                }
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

    private Button createActionButton(DataTable dataTable) {
        Button actionButton = new Button("Cancelar");
        actionButton.setStyle("-fx-background-color: #FF6666; -fx-border-color: #D6DBDF;");

        actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (dataTable.getEvento().isInscrito() ) {
                    dataTable.getEvento().setInscrito(false);
                    switchTutoringDelete();
                    table.refresh();
                }
                // Aquí puedes añadir la lógica para agendar la tutoría
                // Por ejemplo, marcar el evento como inscrito y actualizar la tabla
                dataTable.setEstadoEvento("Agendada");
                eventList.remove(dataTable);
                // Muestra el popup de confirmación
            }
        });

        return actionButton;
    }
    private void adjustColumnWidths() {
        double columnCount = 5.0; // El número total de columnas

        colDia.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colMatter.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colDescription.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colTime.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
        colAction.prefWidthProperty().bind(table.widthProperty().divide(columnCount));
    }
    public void switchToBack() throws IOException {
        App.setRoot("menuStudent");
    }

    private void switchTutoringDelete() {
        VBox popupContent = new VBox();
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setSpacing(20);

        Label messageLabel = new Label("Tutoria cancelada Exitosamente");
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Tutoria cancelada");

        Button agendarButton = new Button("Aceptar");
        agendarButton.setOnAction(e -> {
            popupStage.close();
            try {
                switchDeleteTutoring();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        buttonBox.getChildren().addAll(agendarButton);
        popupContent.getChildren().addAll(messageLabel, buttonBox);

        Scene popupScene = new Scene(popupContent, 300, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
}