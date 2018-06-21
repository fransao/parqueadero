package testdatabuilder;

import parqueadero.dominio.Moto;
import parqueadero.enumerado.EnumTipoVehiculo;

public class MotoTestDataBuilder extends VehiculoTestDataBuilder {

    public static final int CILINDRAJE = 650;
    
    private int cilindraje;
    private EnumTipoVehiculo tipoVehiculo;
    
    public MotoTestDataBuilder (String placa, EnumTipoVehiculo tipoVehiculo) {
        super (placa, tipoVehiculo);
        cilindraje = CILINDRAJE;
        this.tipoVehiculo = EnumTipoVehiculo.CARRO;
    }
    
    public MotoTestDataBuilder conCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }
    
    public void conTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public Moto build() {
        return new Moto (super.build() , cilindraje);
    }

}
