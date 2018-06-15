package parqueadero.unitaria;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import parqueadero.dominio.Moto;
import parqueadero.dominio.Vigilante;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.exception.ParqueaderoException;
import parqueadero.servicio.IGestionVehiculoServicio;
import testdatabuilder.MotoTestDataBuilder;

public class VigilanteTest {

    private static final int CILINDRAJE = 650;
    private static final String PLACA = "AUV456";
    
    @Test
    public void ingresarVehiculoIniciaPlacaA () {
        // arrange
        Moto moto = new MotoTestDataBuilder(PLACA, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        
        IGestionVehiculoServicio gestionVehiculoServicio = mock(IGestionVehiculoServicio.class);
        
        Vigilante vigilante = new Vigilante (gestionVehiculoServicio);
        
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
