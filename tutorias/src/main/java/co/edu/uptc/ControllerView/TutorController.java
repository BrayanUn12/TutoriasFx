package co.edu.uptc.ControllerView;

import java.util.ArrayList;

import co.edu.uptc.App;
import co.edu.uptc.model.Tutor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.google.gson.reflect.TypeToken;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TutorController {

    @FXML
    private TextField nameEvent;

    
    private ArrayList<Tutor> tutores;

    @FXML
    public void addEvent (){
        
    }

    @FXML
    private void switchToAddTutoring () throws IOException{
        App.setRoot("addEventTutor");
    }

    @FXML
    private void showEvents()throws IOException{

    }

    @FXML 
    private void deleteEvents ()throws IOException{

    }

    @FXML
    private void modifyEvents ()throws IOException{

    }

}
