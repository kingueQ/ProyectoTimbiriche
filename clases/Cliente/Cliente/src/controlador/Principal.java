package controlador;

/**
 * Clase principal del juego
 * @author Abraham Quintana y Rafael Soqui
 */
public class Principal {

    public static void main(String[] args) {
        // Declarar la variable servidor fuera del lambda
        Vista vista=new Vista();
        vista.showPantallaInicio();
    }
}
