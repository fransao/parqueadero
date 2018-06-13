package parqueadero.exception;

public class ParqueaderoException extends RuntimeException {

    private static final long serialVersionUID = -1509705912656239740L;

    public ParqueaderoException (String mensaje) {
        super(mensaje);
    }
}
