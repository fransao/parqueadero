package parqueadero.enumerado;

import java.util.stream.Stream;

public enum EnumEstadoParqueo {

    INGRESADO (1), SALIDA(2);
    
    private int estadoParqueo;
    
    private EnumEstadoParqueo(int estadoParqueo) {
        this.estadoParqueo = estadoParqueo;
    }

    public int getEstadoParqueo() {
        return estadoParqueo;
    }
    
    public static EnumEstadoParqueo getEnumEstadoParqueo (int estadoParqueo) {
        return Stream.of(EnumEstadoParqueo.values()).filter(e -> e.getEstadoParqueo() == estadoParqueo).findFirst().orElse(null);
    }
    
}
