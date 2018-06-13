package parqueadero.integracion;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import parqueadero.dominio.Moto;
import parqueadero.dominio.Vigilante;
import parqueadero.persistencia.sistema.SistemaDePersistencia;
import parqueadero.repositorio.RepositorioGestionParqueadero;
import parqueadero.repositorio.RepositorioVehiculo;
import testdatabuilder.MotoTestDataBuilder;

public class VigilanteTest {

    private SistemaDePersistencia sistemaPersistencia;

    private RepositorioVehiculo repositorioVehiculo;
    private RepositorioGestionParqueadero repositorioGestionParqueadero;
    
    private static final int CILINDRAJE = 650;
    private static final String PLACA = "TUV456";
    
    @Before
    public void setUp() {

        sistemaPersistencia = new SistemaDePersistencia();

        repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
        repositorioGestionParqueadero = sistemaPersistencia.obtenerRepositorioGestionparqueadero();

        sistemaPersistencia.iniciar();
    }
    
    @After
    public void tearDown() {
        sistemaPersistencia.terminar();
    }
    
    @Ignore
    @Test
    public void ingresarMotoTest() {
        // arrange
        Moto moto = new MotoTestDataBuilder(PLACA).conCilindraje(CILINDRAJE).build();
        repositorioVehiculo.registrarPlacaVehiculo(moto);
        Vigilante vigilante = new Vigilante ();
        
        // act
        vigilante.ingresarVehiculo(moto, new Date());
        
        // assert
        Assert.assertTrue(vigilante.estaIngresado(moto));
    }

}
