package testdatabuilder;

import parqueadero.dominio.Vehiculo;

public class VehiculoTestDataBuilder {

    private String placa;
    
    public VehiculoTestDataBuilder (String placa) {
        this.placa = placa;
    }
    
    public VehiculoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }
    
    public Vehiculo build() {
        return new Vehiculo(placa);
    }
    
}
