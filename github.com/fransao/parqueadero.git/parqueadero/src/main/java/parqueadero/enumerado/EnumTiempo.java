package parqueadero.enumerado;

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
        for (EnumTiempo enumTiempo: EnumTiempo.values()) {
            if (enumTiempo.getTiempo() == tiempo) {
                return enumTiempo;
            }
        }
        return null;
    }
}
