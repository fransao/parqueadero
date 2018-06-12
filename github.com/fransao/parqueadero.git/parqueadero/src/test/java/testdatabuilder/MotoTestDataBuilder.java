package testdatabuilder;

import parqueadero.dominio.Moto;

public class MotoTestDataBuilder extends VehiculoTestDataBuilder {

    public static final int CILINDRAJE = 650;
    
    private int cilindraje;
    
    public MotoTestDataBuilder () {
        cilindraje = CILINDRAJE;
    }
    
    public MotoTestDataBuilder conCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }
    
    public Moto build() {
        return new Moto (super.build().getPlaca(), cilindraje);
    }
}
