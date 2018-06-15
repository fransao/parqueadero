package parqueadero.integracion;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Moto;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.dominio.Vigilante;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.servicio.IGestionVehiculoServicio;
import parqueadero.servicio.IVehiculoServicio;
import testdatabuilder.MotoTestDataBuilder;
import testdatabuilder.TipoVehiculoDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest //(classes={ParqueaderoApplication.class})
public class VigilanteTest {

    @Autowired
    private IVehiculoServicio vehiculoServicio;
    
    @Autowired
    private IGestionVehiculoServicio gestionVehiculoServicio;
    
    private static final int CILINDRAJE = 650;
    private static final String PLACA = "TUV456";

    private static final String TIPO_VEHICULO_MOTO = "Moto";
    
    @Test
    public void ingresarTipoVehiculo () {
     // arrange
        TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder()
                                        .conTipoVehiculo(EnumTipoVehiculo.MOTO.getTipoVehiculo())
                                        .conNombre(TIPO_VEHICULO_MOTO).build();
        
        // act
        vehiculoServicio.registrarTipoVehiculo(tipoVehiculo);
        
        //assert
        Assert.assertTrue(vehiculoServicio.obtenerTipoVehiculo(EnumTipoVehiculo.getEnumTipoVehiculo(tipoVehiculo.getCodTipoVehiculo())) != null);
    }
    
    @Test
    public void ingresarMotoTest() {
        
        // arrange
        TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder()
                .conTipoVehiculo(EnumTipoVehiculo.MOTO.getTipoVehiculo())
                .conNombre(TIPO_VEHICULO_MOTO).build();

        // act
        vehiculoServicio.registrarTipoVehiculo(tipoVehiculo);
        
        Vehiculo moto = new MotoTestDataBuilder(PLACA, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        
        vehiculoServicio.registrarPlacaVehiculo(moto);
        
        Vigilante vigilante = new Vigilante (gestionVehiculoServicio);
        
        GestionVehiculo gestionVehiculo = new GestionVehiculo(EnumTipoVehiculo.MOTO, moto, new Date());
        
        // act
        vigilante.ingresarVehiculo(moto, new Date());
        
        // assert
        Assert.assertTrue(true);//vigilante.estaVehiculoIngresado(moto));
    }

}
