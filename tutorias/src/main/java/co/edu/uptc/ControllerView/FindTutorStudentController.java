package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.controller.TutorControl;
import co.edu.uptc.model.DataTable;
import co.edu.uptc.model.Estudent;
import co.edu.uptc.model.Tutor;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FindTutorStudentController implements Initializable {

    private TutorControl controller = new TutorControl();
    private ArrayList<Tutor> tutors;

    @FXML
    private Label labelName;

    @FXML
    private Label labelCode;

    @FXML
    private ComboBox<String> comboBox;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Estudent student = InteractionClass.getInstance().getStudent();
        labelName.setText(student.getFirstName() + " " + student.getLastName());
        labelCode.setText(String.valueOf(student.getCodigo()));
        loadTutors();
    }

    public void showMattersTutor() {
        Estudent student = InteractionClass.getInstance().getStudent();
        String selectedTutorName = comboBox.getSelectionModel().getSelectedItem();

        eventList = FXCollections.observableArrayList();
        if (selectedTutorName != null) {
            for (Tutor tutor : tutors) {
                String tutorFullName = tutor.getFirstName() + " " + tutor.getLastName();
                if (selectedTutorName.equals(tutorFullName)) {
                    for (Dia dia : tutor.getCalendarios()) {
                        for (Evento evento : dia.getEventos()){
                            eventList.add(new DataTable(dia.getNombre(), evento));
                        }
                    }
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

    private void loadTutors() {
        Type listType = new TypeToken<ArrayList<Tutor>>() {}.getType();
        tutors = (ArrayList<Tutor>) controller.deserializeObecjtoCollectionfromJson(listType);

        ArrayList<String> listTutors = new ArrayList<>();
        for (Tutor tutor : tutors) {
            listTutors.add(tutor.getFirstName() + " " + tutor.getLastName());
        }

        ObservableList<String> observableListTutors = FXCollections.observableArrayList(listTutors);
        comboBox.setItems(observableListTutors);
    }

    public void switchToBack() throws IOException {
        App.setRoot("menuStudent");
    }
}


