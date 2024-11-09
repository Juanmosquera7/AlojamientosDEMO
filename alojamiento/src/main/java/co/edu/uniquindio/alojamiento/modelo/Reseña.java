package co.edu.uniquindio.alojamiento.modelo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reseña {

    private String id;                // ID único de la reseña
    private Usuario usuario;          // Usuario que hizo la reseña
    private Alojamiento alojamiento;  // Alojamiento reseñado
    private int calificacion;         // Calificación (1 a 5)
    private String comentario;        // Comentario del usuario

    // Métodos adicionales si es necesario
}

