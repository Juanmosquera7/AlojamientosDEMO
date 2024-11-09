package co.edu.uniquindio.alojamiento.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import co.edu.uniquindio.alojamiento.modelo.Alojamiento;
import co.edu.uniquindio.alojamiento.modelo.Oferta;
import co.edu.uniquindio.alojamiento.controladores.ControladorPrincipal;

import java.time.LocalDate;
import java.util.List;

public class InicioControlador {

    private final ControladorPrincipal controladorPrincipal;

    @FXML
    private TextField busquedaAlojamiento;

    @FXML
    private ListView<Alojamiento> listaAlojamientosDisponibles;

    @FXML
    private ListView<Oferta> listaOfertas;

    @FXML
    private Text textoBienvenida;

    // Constructor del controlador
    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    // Método para ir a la ventana de Iniciar Sesión
    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/login.fxml", "Iniciar Sesión");
    }

    // Método para ir a la ventana de Registro de Cliente
    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/registro.fxml", "Registro Persona");
    }

    // Método para buscar alojamientos según el texto de búsqueda
    public void buscarAlojamientos(ActionEvent actionEvent) {
        String busqueda = busquedaAlojamiento.getText().trim();

        if (busqueda.isEmpty()) {
            // Muestra una alerta si no se ha ingresado ningún texto
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Búsqueda vacía");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingresa el nombre de la ciudad o el tipo de alojamiento.");
            alert.showAndWait();
        } else {
            // Aquí puedes agregar lógica para filtrar los alojamientos
            System.out.println("Buscando alojamientos para: " + busqueda);
        }
    }

    // Método para cargar una lista aleatoria de alojamientos disponibles
    public void cargarAlojamientosDisponibles() {
        // Simulamos la carga de alojamientos disponibles aleatorios usando Builder
        List<Alojamiento> alojamientos = obtenerAlojamientosDisponibles();
        listaAlojamientosDisponibles.getItems().setAll(alojamientos);
    }

    // Método para cargar ofertas especiales
    public void cargarOfertasEspeciales() {
        // Simulamos la carga de ofertas usando Builder
        List<Oferta> ofertas = obtenerOfertasEspeciales();
        listaOfertas.getItems().setAll(ofertas);
    }

    // Simulación de obtener alojamientos disponibles utilizando el patrón Builder
    private List<Alojamiento> obtenerAlojamientosDisponibles() {
        // Aquí iría la lógica para obtener alojamientos de la base de datos o de una fuente
        // Usamos el patrón Builder para crear instancias de Alojamiento
        return List.of(
                Alojamiento.builder()
                        .nombre("Alojamiento 1")
                        .direccion("Ciudad 1")
                        .build(),
                Alojamiento.builder()
                        .nombre("Alojamiento 2")
                        .direccion("Ciudad 2")
                        .build(),
                Alojamiento.builder()
                        .nombre("Alojamiento 3")
                        .direccion("Ciudad 3")
                        .build()
        );
    }

    // Simulación de obtener ofertas especiales utilizando el patrón Builder
    private List<Oferta> obtenerOfertasEspeciales() {
        // Aquí iría la lógica para obtener ofertas especiales
        // Usamos el patrón Builder para crear instancias de Oferta
        return List.of(
                Oferta.builder()

                        .descuento(30)
                        .fechaInicio(LocalDate.now())
                        .fechaFin(LocalDate.now().plusDays(7))
                        .build(),
                Oferta.builder()

                        .descuento(20)
                        .fechaInicio(LocalDate.now())
                        .fechaFin(LocalDate.now().plusDays(5))
                        .build()
        );
    }

    // Método para inicializar los datos cuando se carga la vista
    @FXML
    public void initialize() {
        cargarAlojamientosDisponibles();
        cargarOfertasEspeciales();
    }
}



