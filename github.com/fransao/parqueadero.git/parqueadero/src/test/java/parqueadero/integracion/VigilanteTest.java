package parqueadero.integracion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.dominio.Vigilante;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.servicio.IGestionVehiculoServicio;
import parqueadero.servicio.IParqueaderoServicio;
import parqueadero.servicio.IVehiculoServicio;
import testdatabuilder.EstadoParqueoDataBuilder;
import testdatabuilder.MotoTestDataBuilder;
import testdatabuilder.TipoVehiculoDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest //(classes={ParqueaderoApplication.class})
public class VigilanteTest {

    @Autowired
    private IVehiculoServicio vehiculoServicio;
    
    @Autowired
    private IGestionVehiculoServicio gestionVehiculoServicio;
    
    @Autowired
    private IParqueaderoServicio parqueaderoServicio;
    
    private static final int CILINDRAJE = 650;
    private static final String PLACA_01 = "TUV456";
    private static final String PLACA_02 = "XXX999";
    private static final String PLACA_03 = "HHH111";

    private static final String TIPO_VEHICULO_MOTO = "Moto";

    private static final String NOMBRE_PARQUEO_INGRESADO = "Ingresado";
    private static final String NOMBRE_PARQUEO_SALIDA    = "Salida";
    
    @Before
    public void setup() {
        crearTipoVehiculo();
        crearEstadoParqueo();
        crearTarifaVehiculo();
        crearRecargo();
    }
    
    private void crearRecargo() {
        RecargoCilindraje recargoCilindraje = new RecargoCilindraje();
        recargoCilindraje.setIdCilindraje(1);
        recargoCilindraje.setTipoVehiculo(EnumTipoVehiculo.MOTO);
        recargoCilindraje.setCilindrajeDesde(501);
        recargoCilindraje.setCilindrajeHasta(2000);
        recargoCilindraje.setValor(2000);
        
        parqueaderoServicio.registrarRecargo(recargoCilindraje);
        
    }

    private void crearTarifaVehiculo() {
        List<TarifaXTipoVehiculo> listTarifaVehiculo = new ArrayList<>();
        
        TarifaXTipoVehiculo tarifaVehuiculo = new TarifaXTipoVehiculo();
        tarifaVehuiculo.setTipoVehiculo(EnumTipoVehiculo.CARRO);
        tarifaVehuiculo.setUnidadTiempo(EnumTiempo.HORA);
        tarifaVehuiculo.setValor(1000);
        
        listTarifaVehiculo.add(tarifaVehuiculo);
        
        tarifaVehuiculo = new TarifaXTipoVehiculo();
        tarifaVehuiculo.setTipoVehiculo(EnumTipoVehiculo.CARRO);
        tarifaVehuiculo.setUnidadTiempo(EnumTiempo.DIA);
        tarifaVehuiculo.setValor(8000);
        
        listTarifaVehiculo.add(tarifaVehuiculo);
        
        tarifaVehuiculo = new TarifaXTipoVehiculo();
        tarifaVehuiculo.setTipoVehiculo(EnumTipoVehiculo.MOTO);
        tarifaVehuiculo.setUnidadTiempo(EnumTiempo.HORA);
        tarifaVehuiculo.setValor(500);
        
        listTarifaVehiculo.add(tarifaVehuiculo);
        
        tarifaVehuiculo = new TarifaXTipoVehiculo();
        tarifaVehuiculo.setTipoVehiculo(EnumTipoVehiculo.MOTO);
        tarifaVehuiculo.setUnidadTiempo(EnumTiempo.DIA);
        tarifaVehuiculo.setValor(4000);
        
        listTarifaVehiculo.add(tarifaVehuiculo);
        
        parqueaderoServicio.registrarTarifas(listTarifaVehiculo);
        
    }

    private void crearTipoVehiculo() {
        TipoVehiculo tipoVehiculo;
        for (EnumTipoVehiculo enumTipoVehiculo: EnumTipoVehiculo.values()) {
            tipoVehiculo = new TipoVehiculoDataBuilder()
                    .conTipoVehiculo(enumTipoVehiculo)
                    .conNombre(enumTipoVehiculo.name()).build();
            vehiculoServicio.registrarTipoVehiculo(tipoVehiculo);
        }
        
    }

    private void crearEstadoParqueo() {
        EstadoParqueo estadoParqueo;
        for (EnumEstadoParqueo enumEstadoParqueo: EnumEstadoParqueo.values()) {
            estadoParqueo = new EstadoParqueoDataBuilder()
                    .conCodEstadoParqueo(enumEstadoParqueo)
                    .conNombreEstadoParqueo(enumEstadoParqueo.name()).build();

            vehiculoServicio.registrarEstadoParqueo(estadoParqueo);
        }
        
    }
    
    @Test
    public void ingresarTipoVehiculo () {
     // arrange
        TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder()
                                        .conTipoVehiculo(EnumTipoVehiculo.MOTO)
                                        .conNombre(TIPO_VEHICULO_MOTO).build();
        
        // act
        vehiculoServicio.registrarTipoVehiculo(tipoVehiculo);
        
        //assert
        Assert.assertTrue(vehiculoServicio.obtenerTipoVehiculo(tipoVehiculo.getCodTipoVehiculo()) != null);
    }
    
    @Test
    public void ingresarEstadoParqueo () {
        // arrange
        EstadoParqueo estadoParqueo = new EstadoParqueoDataBuilder()
                                        .conCodEstadoParqueo(EnumEstadoParqueo.INGRESADO)
                                        .conNombreEstadoParqueo(NOMBRE_PARQUEO_INGRESADO).build();
        
        // act
        vehiculoServicio.registrarEstadoParqueo(estadoParqueo);
        
        //assert
        Assert.assertTrue(gestionVehiculoServicio.obtenerEstadoParqueo(estadoParqueo) != null);
    }
    
    @Test
    public void ingresarMotoTest() {
        
        // arrange
        Vehiculo moto = new MotoTestDataBuilder(PLACA_01, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        vehiculoServicio.registrarPlacaVehiculo(moto);
        Vigilante vigilante = new Vigilante (gestionVehiculoServicio, parqueaderoServicio);
        
        // act
        vigilante.ingresarVehiculoAParqueadero(moto, new Date());
        
        // assert
        Assert.assertTrue(vigilante.estaVehiculoIngresado(moto));
    }

    @Ignore
    @Test
    public void salidaMotoTest() {
        
        // arrange
        Vehiculo moto = new MotoTestDataBuilder(PLACA_01, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        vehiculoServicio.registrarPlacaVehiculo(moto);
        Vigilante vigilante = new Vigilante (gestionVehiculoServicio, parqueaderoServicio);
        
        // act
        vigilante.ingresarVehiculoAParqueadero(moto, new Date());
        
        // assert
        Assert.assertTrue(vigilante.estaVehiculoIngresado(moto));
    }
    
    @Test
    public void generarCobroTest() {
        // arrange
        Calendar calendarFechaSalida  = Calendar.getInstance();
        calendarFechaSalida.add(Calendar.DAY_OF_MONTH, 2);
        calendarFechaSalida.add(Calendar.HOUR, 4);
        
        Vehiculo moto = new MotoTestDataBuilder(PLACA_02, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        vehiculoServicio.registrarPlacaVehiculo(moto);
        Vigilante vigilante = new Vigilante (gestionVehiculoServicio, parqueaderoServicio);
        
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_02).build();
        
        // act
        vigilante.ingresarVehiculoAParqueadero(moto, new Date());
        
        GestionVehiculo vehiculoIngresado = vigilante.obtenerVehiculoIngresado(vehiculo);
        vehiculoIngresado.setFechaSalida(calendarFechaSalida.getTime());
        
        float valorAPagar = vigilante.generarCobroVechiculoParqueo(vehiculoIngresado);
        
        // assert
        Assert.assertFalse(vigilante.estaVehiculoIngresado(vehiculo));
    }
    
}
