package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.model.DataTable;
import co.edu.uptc.model.Tutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowInfoTutor implements Initializable {

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
    private Button acceptButton;

    private ObservableList<DataTable> eventList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InteractionClass<Tutor> interactionInstance = InteractionClass.getInstance();
        Tutor tutor = interactionInstance.getObject();
        labelName.setText(tutor.getFirstName() + " " + tutor.getLastName());
        eventList = FXCollections.observableArrayList();
        for (Dia dia : tutor.getCalendarios()) {
            for (Evento evento : dia.getEventos()) {
                eventList.add(new DataTable(dia.getNombre(), evento));
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

    public void acceptButton() throws IOException {
        App.setRoot("tutor");
    }
}