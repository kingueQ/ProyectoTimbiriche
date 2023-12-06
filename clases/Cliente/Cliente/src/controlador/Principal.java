package controlador;


public class Principal {

    public static void main(String[] args) {
        // Declarar la variable servidor fuera del lambda
        Vista vista=new Vista();
        vista.showPantallaInicio();
    }
}
