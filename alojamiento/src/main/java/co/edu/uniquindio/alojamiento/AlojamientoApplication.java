package co.edu.uniquindio.alojamiento;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlojamientoApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(AlojamientoApplication.class.getResource("/inicio.fxml"));
        Parent parent = loader.load();


        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Reserva");
        stage.setMaximized(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch(AlojamientoApplication.class, args);
    }
}
