package co.edu.uniquindio.alojamiento.modelo;

import co.edu.uniquindio.alojamiento.modelo.Wallet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {

    private String cedula;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String contrasena;
    private Wallet billetera;

    public void recargarBilletera(double monto) {
        if (this.billetera == null) {
            // Si la billetera es null, la inicializamos con un saldo de 0
            this.billetera = Wallet.builder().saldo(0).build();
        }
        this.billetera.recargar(monto);
    }


    // Métodos adicionales si es necesario
}

