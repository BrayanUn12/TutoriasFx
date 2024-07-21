package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.StudentController;
import co.edu.uptc.model.Estudent;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StudentControl {

    @FXML
    private TextField idStudent;

    @FXML
    private Button next;

    private StudentController controller = new StudentController();
    private ArrayList<Estudent> students;

    /**
     * Metodo que valida que el id de el estudiante este en el archivo de persistencia.
     * @throws IOException
     */
    @FXML
    public void switchToValidId() throws IOException {
        String inputId = idStudent.getText();
        Integer id = Integer.parseInt(inputId);

        Type listType = new TypeToken<ArrayList<Estudent>>() {
        }.getType();

        students = (ArrayList<Estudent>) controller.deserializeObecjtoCollectionfromJson(listType);

        for (Estudent student : students){
            if (student.getId() == id){
                InteractionClass.getInstance(student);
                System.out.println("id encontrado");
                App.setRoot("menuStudent");
            }
        }
    }

    /**
     * Metodo que muestra las tutorias del estudiante disponiobles y permite agragarlas.
     * @throws IOException
     */
    @FXML
    public void switchToAddTutoring() throws IOException {
       App.setRoot("addTutoring");
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
        App.setRoot("main");
    }

    /**
     * Metodo que que muestra las tutorias agendadas.
     * @throws IOException
     */
    @FXML
    public void switchToShowTutoring() throws IOException {
        App.setRoot("main");
    }

    /**
     * Metodo que permite buscar una determinada tutoria 
     * @throws IOException
     */
    @FXML
    public void switchToFindTutoring() throws IOException {
        App.setRoot("main");
    }

}
