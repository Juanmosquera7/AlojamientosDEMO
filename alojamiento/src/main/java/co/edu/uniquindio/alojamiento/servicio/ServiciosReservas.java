package co.edu.uniquindio.alojamiento.servicio;

import co.edu.uniquindio.alojamiento.modelo.*;

import java.time.LocalDate;
import java.util.List;

public interface ServiciosReservas {

    // Método para iniciar sesión en la plataforma
    Usuario login(String correo, String contrasena) throws Exception;

    // Método para registrar un nuevo cliente
    void registrarPersona(String cedula, String nombre, String telefono, String email, String password) throws Exception;

    // Método para listar todos los alojamientos disponibles en la plataforma
    List<Alojamiento> listarAlojamientosDisponibles();

    // Método para realizar una reserva de alojamiento
    Reserva crearReserva(String idAlojamiento, String cedulaCliente, LocalDate fechaEntrada, LocalDate fechaSalida, int numeroHuespedes) throws Exception;

    // Método para listar las reservas de un cliente en particular
    List<Reserva> listarReservasPorCliente(String cedulaCliente);

    // Método para cancelar una reserva
    void cancelarReserva(String idReserva) throws Exception;

    // Método para listar todos los alojamientos gestionados por el administrador
    List<Alojamiento> listarTodosAlojamientos();

    // Método para verificar la disponibilidad de un alojamiento en unas fechas específicas
    boolean verificarDisponibilidad(Alojamiento alojamiento, LocalDate fechaEntrada, LocalDate fechaSalida, int numeroHuespedes);

    // Método para gestionar la billetera virtual del cliente
    void recargarBilletera(String cedulaCliente, double monto) throws Exception;

    // Método para generar una factura para una reserva
    Factura generarFactura(Reserva reserva);

    // Método para listar las reseñas y valoraciones de un alojamiento
    List<Reseña> listarReseñasPorAlojamiento(String idAlojamiento);

    // Método para que los clientes dejen reseñas y valoraciones
    void agregarReseña(String idAlojamiento, String cedulaCliente, String comentario, int calificacion) throws Exception;

    // Método para crear y gestionar ofertas especiales en los alojamientos
    void crearOfertaEspecial(String idAlojamiento, double descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception;
}

