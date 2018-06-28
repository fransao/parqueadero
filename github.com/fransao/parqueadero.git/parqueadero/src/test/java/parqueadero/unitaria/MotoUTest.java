package parqueadero.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.dominio.Moto;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.testdatabuilder.MotoTestDataBuilder;

public class MotoUTest {

    private static final String PLACA = "JHG742";
    private static final int CILINDRAJE = 650;
    
    @Test
    public void crearMotoTest() {
        // arrange
        MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder(PLACA, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE);
        
        // act
        Moto moto = motoTestDataBuilder.build();
        
        // assert
        assertEquals(CILINDRAJE, moto.getCilindraje());
        
    }

}
