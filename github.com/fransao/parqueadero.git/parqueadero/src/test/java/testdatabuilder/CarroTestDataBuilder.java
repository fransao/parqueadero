package testdatabuilder;

import parqueadero.dominio.Carro;

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
        return new Carro (placa);
    }
}
