package testdatabuilder;

import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;

public class VehiculoTestDataBuilder {

    private String placa;
    private EnumTipoVehiculo tipoVehiculo;
    
    public VehiculoTestDataBuilder (String placa, EnumTipoVehiculo tipoVehiculo) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public VehiculoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }
    
    public VehiculoTestDataBuilder conTipVehiculo(EnumTipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }
    
    public Vehiculo build() {
        return new Vehiculo(placa, tipoVehiculo);
    }
    
}
