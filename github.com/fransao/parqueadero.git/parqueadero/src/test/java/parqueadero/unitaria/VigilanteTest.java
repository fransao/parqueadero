package parqueadero.unitaria;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import parqueadero.dominio.Moto;
import parqueadero.dominio.Vigilante;
import parqueadero.exception.ParqueaderoException;
import testdatabuilder.MotoTestDataBuilder;

public class VigilanteTest {

    private static final int CILINDRAJE = 650;
    private static final String PLACA = "AUV456";
    
    @Test
    public void ingresarVehiculoIniciaPlacaA () {
        // arrange
        Moto moto = new MotoTestDataBuilder(PLACA).conCilindraje(CILINDRAJE).build();
        
        Vigilante vigilante = new Vigilante ();
        
        // act
        try {
            vigilante.ingresarVehiculo(moto, new Date());
            fail();
        } catch (ParqueaderoException pe) {
            // assert
            Assert.assertEquals(Vigilante.MSJ_VEHICULO_NO_AUTORIZADO, pe.getMessage());
        }
        
    }
    
}
