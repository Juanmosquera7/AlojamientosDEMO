package co.edu.uniquindio.alojamiento.modelo.factory;

import co.edu.uniquindio.alojamiento.modelo.Factura;

import java.util.List;

public interface TipoAlojamiento {

    List<String> listaServicios();

    String generarFactura(double subtotal, double costoAseo);
}


