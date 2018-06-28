package parqueadero.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.dominio.Carro;
import parqueadero.testdatabuilder.CarroTestDataBuilder;

public class CarroUTest {

    private static final String PLACA = "XYZ123";
    
    @Test
    public void crearCarroTest() {
        // arrange
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conPlaca(PLACA);
        
        // act
        Carro carro = carroTestDataBuilder.build();
        
        // assert
        assertEquals(PLACA, carro.getPlaca());
    }

}
