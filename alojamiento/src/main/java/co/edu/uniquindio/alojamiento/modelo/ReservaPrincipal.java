package co.edu.uniquindio.alojamiento.modelo;

import co.edu.uniquindio.alojamiento.servicio.ServiciosReservas;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReservaPrincipal implements ServiciosReservas {

    private final List<Usuario> usuariosRegistrados = new ArrayList<>();
    private final List<Alojamiento> alojamientos = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();
    private final List<Reseña> reseñas = new ArrayList<>();

    public ReservaPrincipal() {
        cargarAlojamientos();
    }

    private void cargarAlojamientos() {
        alojamientos.add(Alojamiento.builder()
                .id(UUID.randomUUID().toString())
                .nombre("Hotel Estelar")
                .descripcion("Hotel en el centro de Bogotá")
                .direccion("Calle 10 #20-30, Bogotá")
                .capacidadHuespedes(100)
                .costoPorNoche(200000)
                .disponible(true)
                .build());

        alojamientos.add(Alojamiento.builder()
                .id(UUID.randomUUID().toString())
                .nombre("Casa de Campo")
                .descripcion("Casa de campo en las montañas")
                .direccion("Vereda El Roble, Armenia")
                .capacidadHuespedes(6)
                .costoPorNoche(150000)
                .disponible(true)
                .build());
    }

    @Override
    public Usuario login(String correo, String contrasena) throws Exception {
        return usuariosRegistrados.stream()
                .filter(usuario -> usuario.getEmail().equals(correo) && usuario.getContrasena().equals(contrasena))
                .findFirst()
                .orElseThrow(() -> new Exception("Correo o contraseña incorrectos."));
    }

    @Override
    public void registrarPersona(String cedula, String nombre, String telefono, String email, String password) throws Exception {
        if (usuariosRegistrados.stream().anyMatch(u -> u.getCedula().equals(cedula))) {
            throw new Exception("La cédula ya está registrada.");
        }
        Usuario usuario = Usuario.builder()
                .cedula(cedula)
                .nombreCompleto(nombre)
                .telefono(telefono)
                .email(email)
                .contrasena(password)
                .build();
        usuariosRegistrados.add(usuario);
    }

    @Override
    public List<Alojamiento> listarAlojamientosDisponibles() {
        return alojamientos.stream()
                .filter(Alojamiento::isDisponible)
                .toList();
    }

    @Override
    public Reserva crearReserva(String idAlojamiento, String cedulaCliente, LocalDate fechaEntrada, LocalDate fechaSalida, int numeroHuespedes) throws Exception {
        Alojamiento alojamiento = obtenerAlojamiento(idAlojamiento);
        Usuario cliente = obtenerUsuario(cedulaCliente);

        if (alojamiento == null) {
            throw new Exception("El alojamiento no existe.");
        }
        if (cliente == null) {
            throw new Exception("El cliente no está registrado.");
        }
        if (fechaSalida.isBefore(fechaEntrada)) {
            throw new Exception("La fecha de salida no puede ser antes de la fecha de entrada.");
        }
        if (numeroHuespedes > alojamiento.getCapacidadHuespedes()) {
            throw new Exception("El número de huéspedes excede la capacidad del alojamiento.");
        }
        if (!verificarDisponibilidad(alojamiento, fechaEntrada, fechaSalida, numeroHuespedes)) {
            throw new Exception("El alojamiento no está disponible en las fechas seleccionadas.");
        }

        Reserva reserva = Reserva.builder()
                .id(UUID.randomUUID().toString())
                .alojamiento(alojamiento)
                .usuario(cliente)
                .fechaInicio(fechaEntrada)
                .fechaFin(fechaSalida)
                .numeroHuespedes(numeroHuespedes)
                .build();
        reservas.add(reserva);
        return reserva;
    }

    private Alojamiento obtenerAlojamiento(String idAlojamiento) {
        return alojamientos.stream()
                .filter(a -> a.getId().equals(idAlojamiento))
                .findFirst()
                .orElse(null);
    }

    private Usuario obtenerUsuario(String cedula) {
        return usuariosRegistrados.stream()
                .filter(u -> u.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void cancelarReserva(String idReserva) throws Exception {
        Reserva reserva = reservas.stream()
                .filter(r -> r.getId().equals(idReserva))
                .findFirst()
                .orElseThrow(() -> new Exception("Reserva no encontrada."));
        reservas.remove(reserva);
    }

    @Override
    public List<Reserva> listarReservasPorCliente(String cedulaCliente) {
        return reservas.stream()
                .filter(r -> r.getUsuario().getCedula().equals(cedulaCliente))
                .toList();
    }

    @Override
    public List<Alojamiento> listarTodosAlojamientos() {
        return new ArrayList<>(alojamientos);
    }

    @Override
    public boolean verificarDisponibilidad(Alojamiento alojamiento, LocalDate fechaEntrada, LocalDate fechaSalida, int numeroHuespedes) {
        if (numeroHuespedes > alojamiento.getCapacidadHuespedes()) {
            return false;
        }
        return reservas.stream()
                .filter(r -> r.getAlojamiento().equals(alojamiento))
                .noneMatch(r -> (fechaEntrada.isBefore(r.getFechaFin()) && fechaSalida.isAfter(r.getFechaInicio())));
    }

    @Override
    public void recargarBilletera(String cedulaCliente, double monto) throws Exception {
        Usuario cliente = obtenerUsuario(cedulaCliente);
        if (cliente == null) {
            throw new Exception("El cliente no está registrado.");
        }
        cliente.recargarBilletera(monto);
    }

    @Override
    public Factura generarFactura(Reserva reserva) {
        // Accedemos al alojamiento asociado con la reserva y calculamos el costo total
        double total = reserva.getAlojamiento().calcularCostoTotal(reserva.getFechaInicio(), reserva.getFechaFin());

        // Generamos la factura con los detalles necesarios
        return Factura.builder()
                .codigo(UUID.randomUUID())  // Generamos un UUID único para la factura
                .reserva(reserva)            // Asignamos la reserva asociada
                .total(total)                // Asignamos el total calculado
                .fecha(LocalDate.now())      // Fecha actual de la factura
                .build();
    }

    private double calcularCostoTotal(Reserva reserva) {
        // Calculamos el número de días entre la fecha de entrada y salida
        long diasEstancia = java.time.temporal.ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
        // Calculamos el costo total
        return reserva.getAlojamiento().getCostoPorNoche() * diasEstancia;
    }

    @Override
    public List<Reseña> listarReseñasPorAlojamiento(String idAlojamiento) {
        return reseñas.stream()
                .filter(r -> r.getAlojamiento().getId().equals(idAlojamiento))
                .toList();
    }

    @Override
    public void agregarReseña(String idAlojamiento, String cedulaCliente, String comentario, int calificacion) throws Exception {
        Alojamiento alojamiento = obtenerAlojamiento(idAlojamiento);
        Usuario cliente = obtenerUsuario(cedulaCliente);

        if (alojamiento == null) {
            throw new Exception("El alojamiento no existe.");
        }
        if (cliente == null) {
            throw new Exception("El cliente no está registrado.");
        }
        if (calificacion < 1 || calificacion > 5) {
            throw new Exception("La calificación debe estar entre 1 y 5.");
        }

        Reseña reseña = Reseña.builder()
                .alojamiento(alojamiento)
                .usuario(cliente)
                .comentario(comentario)
                .calificacion(calificacion)
                .build();
        reseñas.add(reseña);
    }

    @Override
    public void crearOfertaEspecial(String idAlojamiento, double descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        Alojamiento alojamiento = obtenerAlojamiento(idAlojamiento);

        if (alojamiento == null) {
            throw new Exception("El alojamiento no existe.");
        }
        if (descuento <= 0 || descuento >= 100) {
            throw new Exception("El descuento debe ser mayor que 0 y menor que 100.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new Exception("La fecha de fin no puede ser antes de la fecha de inicio.");
        }

        Oferta oferta = Oferta.builder()
                 // Asignar la descripción de la oferta
                .descuento((int) descuento) // Asignar el descuento (en porcentaje)
                .fechaInicio(fechaInicio) // Asignar la fecha de inicio
                .fechaFin(fechaFin) // Asignar la fecha de fin
                .alojamiento(alojamiento) // Asignar el alojamiento
                .build();

        // Agregar la oferta especial al alojamiento
        alojamiento.agregarOfertaEspecial(oferta);
    }
}






