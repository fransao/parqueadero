package parqueadero.unitaria;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import parqueadero.dominio.Moto;
import parqueadero.dominio.Vigilante;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.exception.ParqueaderoException;
import parqueadero.servicio.IAdministradorParqueaderoServicio;
import parqueadero.servicio.IVigilanteServicio;
import parqueadero.util.Util;
import testdatabuilder.MotoTestDataBuilder;

public class VigilanteTest {

    private static final int CILINDRAJE = 650;
    private static final String PLACA = "AUV456";
    private static final String PLACA_01 = "TUV456";
    
    @Test
    public void ingresarVehiculoIniciaPlacaA () {
        // arrange
        Moto moto = new MotoTestDataBuilder(PLACA, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        
        IVigilanteServicio vigilanteServicio = mock(IVigilanteServicio.class);
        IAdministradorParqueaderoServicio administradorParqueaderoServicio = mock(IAdministradorParqueaderoServicio.class);
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        // act
        try {
            vigilante.registrarIngresoVehiculoAParqueadero(moto, new Date());
            fail();
        } catch (ParqueaderoException pe) {
            // assert
            Assert.assertEquals(Vigilante.MSJ_VEHICULO_NO_AUTORIZADO, pe.getMessage());
        }
        
    }
    
    @Test
    public void obtenerTiempoVehiculoParque() {
        // arrange
        Calendar calendarFechaIngreso = Calendar.getInstance();
        Calendar calendarFechaSalida  = Calendar.getInstance();
        calendarFechaSalida.add(Calendar.DAY_OF_MONTH, 2);
        calendarFechaSalida.add(Calendar.HOUR, 4);
        
        // act
        int diasEntreDosFechas  = Util.getDiasEntreDosFechas(calendarFechaIngreso.getTime(), calendarFechaSalida.getTime());
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(calendarFechaIngreso.getTime(), calendarFechaSalida.getTime());
        
        // assert
        Assert.assertEquals(2, diasEntreDosFechas);
        Assert.assertEquals(4, horasEntreDosFechas);
        
    }
    
    
}
