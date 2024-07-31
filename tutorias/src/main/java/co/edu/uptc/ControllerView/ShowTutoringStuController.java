package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.model.DataTable;
import co.edu.uptc.model.Estudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowTutoringStuController implements Initializable {

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


    private ObservableList<DataTable> eventList;

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
                if (evento.isInscrito() == true) {
                    eventList.add(new DataTable(dia.getNombre(), evento));
                }
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

    public void switchToBack() throws IOException {
        App.setRoot("menuStudent");
    }

}

