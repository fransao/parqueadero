package parqueadero.testdatabuilder;

import parqueadero.dominio.Carro;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;

public class CarroTestDataBuilder {

    private static final String PLACA = "650";
    
    private String placa;
    
    public CarroTestDataBuilder () {
        placa = PLACA;
    }
    
    public CarroTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }
    
    public Carro build () {
        return new Carro (new Vehiculo(placa, EnumTipoVehiculo.CARRO));
    }
}
