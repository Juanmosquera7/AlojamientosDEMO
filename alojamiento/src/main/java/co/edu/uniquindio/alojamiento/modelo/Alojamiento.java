package co.edu.uniquindio.alojamiento.modelo;

import co.edu.uniquindio.alojamiento.modelo.factory.TipoAlojamiento;
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
public class Alojamiento {

    private String id;                   // ID único del alojamiento
    private String nombre;               // Nombre del alojamiento
    private String descripcion;          // Descripción del alojamiento
    private String direccion;            // Dirección del alojamiento
    private int capacidadHuespedes;      // Capacidad máxima de huéspedes
    private float costoPorNoche;         // Costo por noche en el alojamiento
    private List<LocalDate> fechasReservadas;  // Fechas que ya están reservadas
    private boolean disponible;          // Si el alojamiento está disponible o no
    private TipoAlojamiento tipoAlojamiento; // Tipo de alojamiento (p.ej. hotel, cabaña, etc.)
    private List<Oferta> ofertasEspeciales; // Lista de ofertas especiales

    /**
     * Método que verifica si el alojamiento está disponible para las fechas indicadas.
     *
     * @param fechaEntrada Fecha de entrada del cliente
     * @param fechaSalida Fecha de salida del cliente
     * @return True si el alojamiento está disponible, false si ya está reservado
     */
    public boolean estaDisponible(LocalDate fechaEntrada, LocalDate fechaSalida) {
        for (LocalDate fechaReservada : fechasReservadas) {
            if ((fechaEntrada.isBefore(fechaReservada) && fechaSalida.isAfter(fechaReservada)) ||
                    fechaEntrada.equals(fechaReservada) || fechaSalida.equals(fechaReservada)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método para agregar una nueva fecha reservada.
     *
     * @param fecha Fecha que se va a reservar
     */
    public void reservarAlojamiento(LocalDate fecha) {
        if (!fechasReservadas.contains(fecha)) {
            fechasReservadas.add(fecha);
        }
    }

    /**
     * Método para liberar una fecha reservada.
     *
     * @param fecha Fecha que se va a liberar
     */
    public void liberarFecha(LocalDate fecha) {
        fechasReservadas.remove(fecha);
    }

    /**
     * Método para calcular el costo total de la estancia.
     *
     * @param fechaInicio Fecha de inicio de la estancia
     * @param fechaFin Fecha de fin de la estancia
     * @return El costo total de la estancia
     */
    public double calcularCostoTotal(LocalDate fechaInicio, LocalDate fechaFin) {
        long numeroNoches = fechaFin.toEpochDay() - fechaInicio.toEpochDay();
        return costoPorNoche * numeroNoches;
    }

    /**
     * Genera la factura utilizando la lógica del tipo de alojamiento.
     *
     * @param fechaInicio Fecha de inicio de la estancia
     * @param fechaFin Fecha de fin de la estancia
     * @return La factura generada para el alojamiento
     */
    public Factura generarFactura(LocalDate fechaInicio, LocalDate fechaFin) {
        double costoTotal = calcularCostoTotal(fechaInicio, fechaFin);
        double subtotal = costoTotal; // Aquí podrías añadir lógica de costos adicionales si es necesario
        double costoAseo = 0.0; // Puedes ajustar este valor dependiendo de la lógica de tu negocio

        // Generar un UUID único para la factura
        UUID codigo = UUID.randomUUID();

        // Obtener la fecha actual para la factura
        LocalDate fechaFactura = LocalDate.now();

        // Calcular el total (aquí puedes añadir otros costos si es necesario)
        double total = subtotal + costoAseo;

        // Crear la factura con el constructor adecuado
        return Factura.builder()
                .codigo(codigo)
                .fecha(fechaFactura)
                .subtotal(subtotal)
                .total(total)
                .build();
    }

    /**
     * Agrega una oferta especial al alojamiento.
     *
     * @param oferta Oferta especial que se va a agregar
     */
    public void agregarOfertaEspecial(Oferta oferta) {
        if (this.ofertasEspeciales == null) {
            this.ofertasEspeciales = new ArrayList<>();
        }
        this.ofertasEspeciales.add(oferta);
    }
}



