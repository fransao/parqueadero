package parqueadero.testdatabuilder;

import parqueadero.dominio.Moto;
import parqueadero.enumerado.EnumTipoVehiculo;

public class MotoTestDataBuilder extends VehiculoTestDataBuilder {

    public static final int CILINDRAJE = 650;
    
    private int cilindraje;
    
    public MotoTestDataBuilder (String placa, EnumTipoVehiculo tipoVehiculo) {
        super (placa, tipoVehiculo);
        cilindraje = CILINDRAJE;
    }
    
    public MotoTestDataBuilder conCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }
    
    public Moto build() {
        return new Moto (super.build() , cilindraje);
    }

}
