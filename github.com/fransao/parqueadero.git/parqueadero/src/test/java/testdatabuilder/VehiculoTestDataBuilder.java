package testdatabuilder;

import parqueadero.dominio.Vehiculo;

public class VehiculoTestDataBuilder {

    private static final String PLACA = "ABC123";
    
    private String placa;
    
    public VehiculoTestDataBuilder () {
        placa = PLACA;
    }
    
    public VehiculoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }
    
    public Vehiculo build() {
        return new Vehiculo(placa);
    }
    
}
