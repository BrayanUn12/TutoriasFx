package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.StudentController;
import co.edu.uptc.model.Estudent;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentControl implements Initializable {

    @FXML
    private TextField idStudent;

    @FXML
    private Label labelName;

    @FXML
    private Label labelCode;

    @FXML
    private Button addTutoring;

    @FXML
    private Button deleteTutoring;

    @FXML
    private Button showTutoring;

    private StudentController controller = new StudentController();
    private ArrayList<Estudent> students;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Estudent student = InteractionClass.getInstance().getStudent();
        labelName.setText(student.getFirstName() + " " + student.getLastName() );
        String code = String.valueOf(student.getCodigo());
        labelCode.setText(code);

        // Modificar el tamaño de los botones
        setButtonSize(addTutoring, 210, 30);
        setButtonSize(deleteTutoring, 210, 30);
        setButtonSize(showTutoring, 210, 30);
    }

    private void setButtonSize(Button button, double width, double height) {
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setMinWidth(width);
        button.setMinHeight(height);
        button.setMaxWidth(width);
        button.setMaxHeight(height);
    }

    /**
     * Metodo que valida que el id de el estudiante este en el archivo de persistencia.
     *
     * @throws IOException
     */
    @FXML
    public void switchToValidId() throws IOException {
        String inputId = idStudent.getText();
        Integer id = Integer.parseInt(inputId);

        Type listType = new TypeToken<ArrayList<Estudent>>() {
        }.getType();

        students = (ArrayList<Estudent>) controller.deserializeObecjtoCollectionfromJson(listType);

        for (Estudent student : students) {
            if (student.getId() == id) {
                InteractionClass.getInstance(student);
                System.out.println("id encontrado");
                App.setRoot("menuStudent");
            }
        }
    }

    /**
     * Metodo que muestra las tutorias del estudiante disponiobles y permite agragarlas.
     *
     * @throws IOException
     */
    @FXML
    public void switchToAddTutoring() throws IOException {
        App.setRoot("addTutoringStudent");
    }

    /**
     *
     */
    @FXML
    public void switchToBack() throws IOException {
        App.setRoot("main");
    }

    /**
     * Metodo que permite eliminar una tutoria
     * @throws IOException
     */
    @FXML
    public void switchToDeleteTutoring() throws IOException {
        App.setRoot("deleteTutoringStudent");
    }

    /**
     * Metodo que que muestra las tutorias agendadas.
     * @throws IOException
     */
    @FXML
    public void switchToShowTutoring() throws IOException {
        App.setRoot("showTutoringStudent");
    }

    /**
     * Metodo que permite buscar una determinada tutoria 
     * @throws IOException
     */
    @FXML
    public void switchClose () throws IOException {
        App.setRoot("main");
    }
}
