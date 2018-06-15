package parqueadero.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;
import testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoTest {

    private static final String PLACA = "AAA123";
    
    @Test
    public void crearVehiculoTest() {
        // arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder(PLACA, EnumTipoVehiculo.CARRO);
        
        // act
        Vehiculo vehiculo = vehiculoTestDataBuilder.build();
        
        // assert
        assertEquals(PLACA, vehiculo.getPlaca());
    }

}
