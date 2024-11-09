package co.edu.uniquindio.alojamiento.modelo.factory;

import co.edu.uniquindio.alojamiento.modelo.Factura;
import co.edu.uniquindio.alojamiento.modelo.factory.TipoAlojamiento;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Builder
public class Apartamento implements TipoAlojamiento {

    private String descripcion;
    private double precioPorNoche;
    private double costoAseo;

    @Override
    public List<String> listaServicios() {
        return Arrays.asList("Wi-Fi", "Aire acondicionado", "Cocina equipada");
    }

    @Override
    public String generarFactura(double subtotal, double costoAseo) {
        // Calcular el total
        double total = subtotal + costoAseo;

        // Generar el ID único y la fecha
        String idFactura = UUID.randomUUID().toString();
        LocalDateTime fecha = LocalDateTime.now();

        // Generar los detalles de la factura en un formato legible
        return "Factura Apartamento\n" +
                "ID Factura: " + idFactura + "\n" +
                "Fecha: " + fecha + "\n" +
                "Subtotal: " + subtotal + "\n" +
                "Costo Aseo: " + costoAseo + "\n" +
                "Total: " + total;
    }
}



