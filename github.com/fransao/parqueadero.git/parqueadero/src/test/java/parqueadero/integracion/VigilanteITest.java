package parqueadero.integracion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static parqueadero.util.ConstanteManager.MSJ_MAXIMO_MOTOS_PARQUEADOOS;
import static parqueadero.util.ConstanteManager.MSJ_MAXIMO_CARROS_PARQUEADOS;

import parqueadero.dominio.EEstadoParqueo;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.dominio.Vigilante;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.exception.ParqueaderoException;
import parqueadero.servicio.IAdministradorParqueaderoServicio;
import parqueadero.servicio.IVigilanteServicio;
import parqueadero.testdatabuilder.CarroTestDataBuilder;
import parqueadero.testdatabuilder.EstadoParqueoDataBuilder;
import parqueadero.testdatabuilder.MotoTestDataBuilder;
import parqueadero.testdatabuilder.TipoVehiculoDataBuilder;
import parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import parqueadero.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest //(classes={ParqueaderoApplication.class})
public class VigilanteITest {

    @Autowired
    private IVigilanteServicio vigilanteServicio;
    
    @Autowired
    private IAdministradorParqueaderoServicio administradorParqueaderoServicio;
    
    private static final int CILINDRAJE = 650;
    private static final String PLACA_MOTO = "TUV456";
    private static final String PLACA_SALIDA_MOTO = "SALM010";
    private static final String PLACA_MAXIMO_10_MOTOS = "TCBV053";
    private static final String PLACA_MAXIMO_20_CARROS = "ZXO226";
    private static final String PLACA_CREAR_10_MOTOS = "MOTO";
    private static final String PLACA_CREAR_20_CARROS = "CARR";
    private static final String PLACA_CARRO_COBRAR = "XXX999";

    private static final String TIPO_VEHICULO_MOTO = "Moto";

    private static final String NOMBRE_PARQUEO_INGRESADO = "Ingresado";
    
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
        
        administradorParqueaderoServicio.registrarRecargo(recargoCilindraje);
        
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
        
        administradorParqueaderoServicio.registrarTarifas(listTarifaVehiculo);
        
    }

    private void crearTipoVehiculo() {
        TipoVehiculo tipoVehiculo;
        for (EnumTipoVehiculo enumTipoVehiculo: EnumTipoVehiculo.values()) {
            tipoVehiculo = new TipoVehiculoDataBuilder()
                    .conTipoVehiculo(enumTipoVehiculo)
                    .conNombre(enumTipoVehiculo.name()).build();
            administradorParqueaderoServicio.registrarTipoVehiculo(tipoVehiculo);
        }
        
    }

    private void crearEstadoParqueo() {
        EEstadoParqueo eEstadoParqueo;
        for (EnumEstadoParqueo enumEstadoParqueo: EnumEstadoParqueo.values()) {
            eEstadoParqueo = new EstadoParqueoDataBuilder()
                    .conCodEstadoParqueo(enumEstadoParqueo)
                    .conNombreEstadoParqueo(enumEstadoParqueo.name()).build();

            administradorParqueaderoServicio.registrarEstadoParqueo(eEstadoParqueo);
        }
        
    }
    
    @Test
    public void ingresarTipoVehiculo () {
     // arrange
        TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder()
                                        .conTipoVehiculo(EnumTipoVehiculo.MOTO)
                                        .conNombre(TIPO_VEHICULO_MOTO).build();
        
        // act
        administradorParqueaderoServicio.registrarTipoVehiculo(tipoVehiculo);
        
        //assert
        Assert.assertTrue(administradorParqueaderoServicio.obtenerTipoVehiculo(tipoVehiculo.getCodTipoVehiculo()) != null);
    }
    
    @Test
    public void ingresarEstadoParqueo () {
        // arrange
        EEstadoParqueo eEstadoParqueo = new EstadoParqueoDataBuilder()
                                        .conCodEstadoParqueo(EnumEstadoParqueo.INGRESADO)
                                        .conNombreEstadoParqueo(NOMBRE_PARQUEO_INGRESADO).build();
        
        // act
        administradorParqueaderoServicio.registrarEstadoParqueo(eEstadoParqueo);
        
        //assert
        Assert.assertTrue(administradorParqueaderoServicio.obtenerEstadoParqueo(eEstadoParqueo) != null);
    }
    
    @Test
    public void registrarIngresoMotoTest() {
        
        // arrange
        Vehiculo moto = new MotoTestDataBuilder(PLACA_MOTO, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        // act
        vigilante.desocuparParqueadero();
        
        vigilante.registrarIngresoVehiculoAParqueadero(moto, new Date());
        
        // assert
        Assert.assertTrue(vigilante.estaVehiculoIngresado(moto));
    }

    @Test
    public void registrarIngresoMaximo10MotoTest() {
        
        // arrange
     // act
        try {
            registrar10Motos();

            Vehiculo moto = new MotoTestDataBuilder(PLACA_MAXIMO_10_MOTOS, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
            vigilanteServicio.registrarPlacaVehiculo(moto);

            Vigilante vigilante = new Vigilante(vigilanteServicio, administradorParqueaderoServicio);

            vigilante.validarDisponibilidad(moto);
        } catch (ParqueaderoException pe) {
            // assert
            Assert.assertEquals(MSJ_MAXIMO_MOTOS_PARQUEADOOS, pe.getMessage());
        }
    }
    
    @Test
    public void registrarIngresoMaximo20CarrosTest() {
        
        // arrange
        try {
            registrar20Carros();

            Vehiculo carro = new CarroTestDataBuilder().conPlaca(PLACA_MAXIMO_20_CARROS).build();
            vigilanteServicio.registrarPlacaVehiculo(carro);

            Vigilante vigilante = new Vigilante(vigilanteServicio, administradorParqueaderoServicio);

            // act
            vigilante.validarDisponibilidad(carro);
        } catch (ParqueaderoException pe) {
            // assert
            Assert.assertEquals(MSJ_MAXIMO_CARROS_PARQUEADOS, pe.getMessage());
        }
    }
    
    private void registrar10Motos() {
        
        Vehiculo moto;
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        for (int i=0; i<=9; i++) {
            moto = new MotoTestDataBuilder(PLACA_CREAR_10_MOTOS + i, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE+1).build();
            vigilanteServicio.registrarPlacaVehiculo(moto);
            vigilante.registrarIngresoVehiculoAParqueadero(moto, new Date());
        }
    }

    private void registrar20Carros() {
        
        Vehiculo carro;
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        for (int i=0; i<=19; i++) {
            carro = new CarroTestDataBuilder().conPlaca(PLACA_CREAR_20_CARROS + i).build();
            vigilanteServicio.registrarPlacaVehiculo(carro);
            vigilante.registrarIngresoVehiculoAParqueadero(carro, new Date());
        }
    }

    @Test
    public void registrarSalidaMotoTest() {
        
        // arrange
        Vehiculo moto = new MotoTestDataBuilder(PLACA_SALIDA_MOTO, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        vigilanteServicio.registrarPlacaVehiculo(moto);
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        // act
        vigilante.registrarIngresoVehiculoAParqueadero(moto, new Date());
        vigilante.registrarSalidaVehiculoParqueadero(moto, new Date());
        
        // assert
        Assert.assertFalse(vigilante.estaVehiculoIngresado(moto));
    }
    
    @Test
    public void cobrarPorDiaTest() {
        // arrange
        Date fechaIngreso = new Date();
        
        Calendar calendarFechaSalida  = Calendar.getInstance();
        calendarFechaSalida.add(Calendar.DAY_OF_MONTH, 2);
        calendarFechaSalida.add(Calendar.HOUR, 4);
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_CARRO_COBRAR).conTipVehiculo(EnumTipoVehiculo.CARRO).build();
        
        // act
        List<TarifaXTipoVehiculo> listTarifa = administradorParqueaderoServicio.obtenerTarifasXTipoVehiculo();
        
        int diasEntreDosFechas  = Util.getDiasEntreDosFechas(fechaIngreso, calendarFechaSalida.getTime());
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(fechaIngreso, calendarFechaSalida.getTime());
        
        if (horasEntreDosFechas >= 9) {
            diasEntreDosFechas += 1;
            horasEntreDosFechas = 0;
        }
        
        // assert
        Assert.assertTrue(vigilante.calcularCobroPorDia(listTarifa, vehiculo, diasEntreDosFechas) > 0);
    }
    
    @Test
    public void cobrarPorHoraTest() {
        // arrange
        Date fechaIngreso = new Date();
        
        Calendar calendarFechaSalida  = Calendar.getInstance();
        calendarFechaSalida.add(Calendar.DAY_OF_MONTH, 2);
        calendarFechaSalida.add(Calendar.HOUR, 4);
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_CARRO_COBRAR).conTipVehiculo(EnumTipoVehiculo.CARRO).build();
        
        // act
        List<TarifaXTipoVehiculo> listTarifa = administradorParqueaderoServicio.obtenerTarifasXTipoVehiculo();
        
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(fechaIngreso, calendarFechaSalida.getTime());
        
        if (horasEntreDosFechas >= 9) {
            horasEntreDosFechas = 0;
        }
        
        // assert
        Assert.assertTrue(vigilante.calcularCobroPorHora(listTarifa, vehiculo, horasEntreDosFechas) >= 0);
    }
    
    @Test
    public void cobrarPorDiaYHoraTest() {
        // arrange
        Date fechaIngreso = new Date();
        
        Calendar calendarFechaSalida  = Calendar.getInstance();
        calendarFechaSalida.add(Calendar.DAY_OF_MONTH, 2);
        calendarFechaSalida.add(Calendar.HOUR, 4);
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_CARRO_COBRAR).conTipVehiculo(EnumTipoVehiculo.CARRO).build();
        
        // act
        List<TarifaXTipoVehiculo> listTarifa = administradorParqueaderoServicio.obtenerTarifasXTipoVehiculo();
        
        int diasEntreDosFechas  = Util.getDiasEntreDosFechas(fechaIngreso, calendarFechaSalida.getTime());
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(fechaIngreso, calendarFechaSalida.getTime());
        
        if (horasEntreDosFechas >= 9) {
            diasEntreDosFechas += 1;
            horasEntreDosFechas = 0;
        }
        
        // assert
        Assert.assertTrue(vigilante.calcularCobroPorDia(listTarifa, vehiculo, diasEntreDosFechas) > 0);
        Assert.assertTrue(vigilante.calcularCobroPorHora(listTarifa, vehiculo, horasEntreDosFechas) >= 0);
    }
    
    @Test
    public void cobrarMotoMayorCilindrajeTest() {
        // arrange
        Date fechaIngreso = new Date();
        float totalAPagar = 0.0f;
        
        Calendar calendarFechaSalida  = Calendar.getInstance();
        calendarFechaSalida.add(Calendar.DAY_OF_MONTH, 2);
        calendarFechaSalida.add(Calendar.HOUR, 4);
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        Vehiculo vehiculo = new MotoTestDataBuilder(PLACA_CARRO_COBRAR, EnumTipoVehiculo.MOTO).conCilindraje(CILINDRAJE).build();
        
        // act
        List<TarifaXTipoVehiculo> listTarifa = administradorParqueaderoServicio.obtenerTarifasXTipoVehiculo();
        RecargoCilindraje recargoVehiculo    = administradorParqueaderoServicio.obtenerRecargo(vehiculo);
        
        int diasEntreDosFechas  = Util.getDiasEntreDosFechas(fechaIngreso, calendarFechaSalida.getTime());
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(fechaIngreso, calendarFechaSalida.getTime());
        
        if (horasEntreDosFechas >= 9) {
            diasEntreDosFechas += 1;
            horasEntreDosFechas = 0;
        }
        
        totalAPagar = vigilante.calcularCobroPorDia (listTarifa, vehiculo, diasEntreDosFechas);
        totalAPagar += vigilante.calcularCobroPorHora (listTarifa, vehiculo, horasEntreDosFechas);
        
        if (recargoVehiculo != null) {
            totalAPagar +=  recargoVehiculo.getValor();
        }
        
        // assert
        Assert.assertTrue(totalAPagar > 0);
    }
    
}
