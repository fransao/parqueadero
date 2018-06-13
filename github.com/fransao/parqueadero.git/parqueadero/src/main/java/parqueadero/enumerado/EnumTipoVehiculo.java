package parqueadero.enumerado;

public enum EnumTipoVehiculo {

    MOTO(1), CARRO(2);
    
    private int tipoVehiculo;
    
    private EnumTipoVehiculo (int tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public int getTipoVehiculo() {
        return tipoVehiculo;
    }
    
    public static EnumTipoVehiculo getEnumTipoVehiculo(int tipoVehiculo) {
        for (EnumTipoVehiculo enumTipoVehiculo: EnumTipoVehiculo.values()) {
            if (enumTipoVehiculo.getTipoVehiculo() == tipoVehiculo) {
                return enumTipoVehiculo;
            }
        }
        
        return null;
    }
}
