package co.edu.uptc.ControllerView;

import co.edu.uptc.App;
import co.edu.uptc.controller.StudentController;
import co.edu.uptc.controller.TutorControl;
import co.edu.uptc.model.Estudent;
import co.edu.uptc.model.Tutor;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainController {

    private StudentController controllerStudent = new StudentController();
    private TutorControl controllerTutor = new TutorControl();

    private ArrayList<Estudent> students;
    private ArrayList<Tutor> tutors;

    @FXML
    private TextField idUser;

    @FXML
    public void switchToUser() throws IOException {
        try {
            if (idUser.getText().equals("") || idUser.getText() == null ) {
                alertShow("Tiene que ingresar su ID");
            }else {
                if (findIdStudent(idUser) != null){
                    App.setRoot("menuStudent");
                }else if (findIdTutor(idUser) != null){
                    App.setRoot("tutor");
                }else {
                    alertShow("El ID no existe");
                }
            }
        }catch (Exception e){
            alertShow("Información no valida, ingrese su ID");
            e.printStackTrace();
        }
    }

    public void alertShow (String massage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.show();
    }


    public String findIdStudent(TextField idUser) throws IOException {
        String id = idUser.getText();
        Integer idStudent = Integer.parseInt(id);

        Type listType = new TypeToken<ArrayList<Estudent>>() {
        }.getType();

        students = (ArrayList<Estudent>) controllerStudent.deserializeObecjtoCollectionfromJson(listType);

        for (Estudent student : students) {
            if (student.getId() == idStudent) {
                InteractionClass.getInstance(student);
                System.out.println("id encontrado");
                String idS = String.valueOf(student.getId());
                return idS;
            }
        }
        return null;
    }

    public String findIdTutor(TextField idUser) throws IOException {
        String id = idUser.getText();
        Integer idTutor = Integer.parseInt(id);

        Type listType = new TypeToken<ArrayList<Tutor>>() {
        }.getType();

        tutors = (ArrayList<Tutor>) controllerTutor.deserializeObecjtoCollectionfromJson(listType);

        for (Tutor tutor : tutors) {
            if (tutor.getId() == idTutor) {
                InteractionClass.getInstance(tutor);
                System.out.println("id encontrado");
                String idT = String.valueOf(tutor.getId());
                return idT;
            }
        }
        return null;
    }
}
