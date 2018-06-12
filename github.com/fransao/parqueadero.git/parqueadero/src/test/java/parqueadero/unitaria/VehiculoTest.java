package parqueadero.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoTest {

    private static final String PLACA = "AAA123";
    
    @Test
    public void crearVehiculoTest() {
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA);
    }

}
