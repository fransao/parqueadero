package parqueadero.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.dominio.Vehiculo;
import testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoTest {

    private static final String PLACA = "AAA123";
    
    @Test
    public void crearVehiculoTest() {
        // arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder(PLACA);
        
        // act
        Vehiculo vehiculo = vehiculoTestDataBuilder.build();
        
        // assert
        assertEquals(PLACA, vehiculo.getPlaca());
    }

}
