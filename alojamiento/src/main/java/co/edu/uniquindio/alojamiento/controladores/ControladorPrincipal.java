package co.edu.uniquindio.alojamiento.controladores;

import co.edu.uniquindio.alojamiento.modelo.*;
import co.edu.uniquindio.alojamiento.servicio.ServiciosReservas;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ControladorPrincipal implements ServiciosReservas {

    private static ControladorPrincipal INSTANCIA;

    private final ReservaPrincipal reservaPrincipal;

    // Constructor privado para implementar Singleton
    private ControladorPrincipal() {
        reservaPrincipal = new ReservaPrincipal();  // Asumí que tienes una implementación concreta de ServiciosReservas
    }

    // Método para obtener la instancia única de ControladorPrincipal
    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    @Override
    public Usuario login(String correo, String contrasena) throws Exception {
        return reservaPrincipal.login(correo, contrasena);
    }

    @Override
    public void registrarPersona(String cedula, String nombre, String telefono, String email, String password) throws Exception {
        reservaPrincipal.registrarPersona(cedula, nombre, telefono, email, password);
    }

    @Override
    public List<Alojamiento> listarAlojamientosDisponibles() {
        return reservaPrincipal.listarAlojamientosDisponibles();
    }

    @Override
    public Reserva crearReserva(String idAlojamiento, String cedulaCliente, LocalDate fechaEntrada, LocalDate fechaSalida, int numeroHuespedes) throws Exception {
        return reservaPrincipal.crearReserva(idAlojamiento, cedulaCliente, fechaEntrada, fechaSalida, numeroHuespedes);
    }

    @Override
    public List<Reserva> listarReservasPorCliente(String cedulaCliente) {
        return reservaPrincipal.listarReservasPorCliente(cedulaCliente);
    }

    @Override
    public void cancelarReserva(String idReserva) throws Exception {
        reservaPrincipal.cancelarReserva(idReserva);
    }

    @Override
    public List<Alojamiento> listarTodosAlojamientos() {
        return reservaPrincipal.listarTodosAlojamientos();
    }

    @Override
    public boolean verificarDisponibilidad(Alojamiento alojamiento, LocalDate fechaEntrada, LocalDate fechaSalida, int numeroHuespedes) {
        return reservaPrincipal.verificarDisponibilidad(alojamiento, fechaEntrada, fechaSalida, numeroHuespedes);
    }

    @Override
    public void recargarBilletera(String cedulaCliente, double monto) throws Exception {
        reservaPrincipal.recargarBilletera(cedulaCliente, monto);
    }

    @Override
    public Factura generarFactura(Reserva reserva) {
        return reservaPrincipal.generarFactura(reserva);
    }

    @Override
    public List<Reseña> listarReseñasPorAlojamiento(String idAlojamiento) {
        return reservaPrincipal.listarReseñasPorAlojamiento(idAlojamiento);
    }

    @Override
    public void agregarReseña(String idAlojamiento, String cedulaCliente, String comentario, int calificacion) throws Exception {
        reservaPrincipal.agregarReseña(idAlojamiento, cedulaCliente, comentario, calificacion);
    }

    @Override
    public void crearOfertaEspecial(String idAlojamiento, double descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        reservaPrincipal.crearOfertaEspecial(idAlojamiento, descuento, fechaInicio, fechaFin);
    }

    // Método para mostrar alertas
    public void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para navegar a otras ventanas
    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
            // Cargar la vista FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la ventana actual
    public void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}

