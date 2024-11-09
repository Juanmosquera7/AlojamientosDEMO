package co.edu.uniquindio.alojamiento.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Oferta {

    private String descripcion;    // Descripción de la oferta
    private int descuento;         // Porcentaje de descuento de la oferta
    private LocalDate fechaValidez; // Fecha hasta cuando la oferta es válida
    private Alojamiento alojamiento; // Alojamiento asociado a esta oferta (si aplica)
    private LocalDate fechaInicio;  // Fecha de inicio de la oferta
    private LocalDate fechaFin;     // Fecha de fin de la oferta

    // Método toString para representar la oferta en texto
    @Override
    public String toString() {
        return descripcion + " - " + descuento + "% de descuento, válida desde: "
                + fechaInicio + " hasta: " + fechaFin;
    }
}


