package parqueadero.enumerado;

import java.util.stream.Stream;

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
        return Stream.of(EnumTipoVehiculo.values()).filter(e -> e.getTipoVehiculo() == tipoVehiculo).findFirst().orElse(null);
    }
}
