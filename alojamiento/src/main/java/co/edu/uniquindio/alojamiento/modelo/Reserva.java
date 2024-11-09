package co.edu.uniquindio.alojamiento.modelo;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class Reserva {

    private String id;
    private Usuario usuario;
    private Alojamiento alojamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numeroHuespedes;
    private Factura factura; // Relación con la clase Factura

    // Métodos adicionales si es necesario
}
