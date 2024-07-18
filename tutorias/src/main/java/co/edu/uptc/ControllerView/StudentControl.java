package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.Dia;
import co.edu.uptc.controller.Evento;
import co.edu.uptc.controller.StudentController;
import co.edu.uptc.model.Estudent;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StudentControl {

    @FXML
    private TextField idStudent;
    @FXML
    private Label tutoring;

    @FXML
    private Button next;

    private StudentController controller = new StudentController();
    private ArrayList<Estudent> students;

    @FXML
    public void switchToValidId() throws IOException {
        String inputId = idStudent.getText();
        Integer id = Integer.parseInt(inputId);
        Type listType = new TypeToken<ArrayList<Estudent>>() {
        }.getType();
        students = (ArrayList<Estudent>) controller.deserializeObecjtoCollectionfromJson(listType);

        boolean studentFound = false;
        for (Estudent student : students) {
            if (id == student.getId()) {
                System.out.println("id encontrado");
                App.setRoot("menuStudent");
                studentFound = true;
            }
        }
        if (!studentFound) {
            System.out.println("Student not found");
            App.setRoot("student");
        }
    }

    @FXML
    public void switchToAddTutoring() throws IOException {
        App.setRoot("addTutoring");
    }

    @FXML
    public void switchToBack() throws IOException {
        App.setRoot("main");
    }
    @FXML
    public void switchToDeleteTutoring() throws IOException {
        App.setRoot("tutor");
    }
    @FXML
    public void switchToShowTutoring() throws IOException {
        App.setRoot("tutor");
    }
    @FXML
    public void switchToFindTutoring() throws IOException {
        App.setRoot("tutor");
    }

}
