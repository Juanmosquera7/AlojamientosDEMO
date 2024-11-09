module co.edu.uniquindio.reservasuq {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires org.simplejavamail.core;
    requires org.simplejavamail;

    // Exposición de los paquetes necesarios
    opens co.edu.uniquindio.alojamiento to javafx.fxml;
    exports co.edu.uniquindio.alojamiento;
    exports co.edu.uniquindio.alojamiento.controladores;
    opens co.edu.uniquindio.alojamiento.controladores to javafx.fxml;
    exports co.edu.uniquindio.alojamiento.modelo;
    opens co.edu.uniquindio.alojamiento.modelo to javafx.fxml;
}