package parqueadero.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.dominio.Moto;
import testdatabuilder.MotoTestDataBuilder;

public class MotoTest {

    private static final String PLACA = "JHG742";
    private static final int CILINDRAJE = 650;
    
    @Test
    public void crearMotoTest() {
        // arrange
        MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder(PLACA).conCilindraje(CILINDRAJE);
        
        // act
        Moto moto = motoTestDataBuilder.build();
        
        // assert
        assertEquals(CILINDRAJE, moto.getCilindraje());
        
    }

}
