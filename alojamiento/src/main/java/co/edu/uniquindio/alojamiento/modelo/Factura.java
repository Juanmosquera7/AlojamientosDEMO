package co.edu.uniquindio.alojamiento.modelo;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Factura {

    private UUID codigo;
    private LocalDate fecha;
    private double subtotal;
    private double total;
    private Reserva reserva;  // Agregamos el campo reserva

}


