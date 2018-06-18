package parqueadero.enumerado;

import java.util.stream.Stream;

public enum EnumTiempo {

    HORA(1),
    DIA (2);
    
    private int tiempo;
    
    private EnumTiempo (int tiempo) {
        this.tiempo = tiempo;
    }
    
    public int getTiempo() {
        return tiempo;
    }
    
    public static EnumTiempo getEnum(int tiempo) {
        return Stream.of(EnumTiempo.values()).filter(t -> t.getTiempo() == tiempo).findFirst().orElse(null);
    }
}
