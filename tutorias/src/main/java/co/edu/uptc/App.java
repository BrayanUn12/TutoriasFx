package co.edu.uptc;

import javafx.application.Application;//Importacion que permite crear la aplicacion
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene; //Permite mostrar una ventana emergente, contenedor donde se muestran todos los nodos utilizado en el proyecto

import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("GESTIÓN DE TUTORIAS");
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(); //Metodo que permite llamar el metodo Start.
    }

}