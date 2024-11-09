package co.edu.uniquindio.alojamiento.modelo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wallet {

    private double saldo;

    public void recargar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
        } else {
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }
    }

    public boolean tieneFondosSuficientes(double monto) {
        return saldo >= monto;
    }

    public void descontar(double monto) {
        if (tieneFondosSuficientes(monto)) {
            saldo -= monto;
        } else {
            throw new IllegalArgumentException("Fondos insuficientes.");
        }
    }
}

