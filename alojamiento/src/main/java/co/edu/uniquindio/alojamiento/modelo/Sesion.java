package co.edu.uniquindio.alojamiento.modelo;


import lombok.Getter;
import lombok.Setter;


public class Sesion {


    public static Sesion INSTANCIA;

    @Getter @Setter
    private Usuario persona;


    private Sesion() {
    }


    public static Sesion getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Sesion();
        }
        return INSTANCIA;
    }


    public void cerrarSesion() {
        persona = null;
    }


}
