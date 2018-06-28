package parqueadero;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.servicio.IAdministradorParqueaderoServicio;

@SpringBootApplication
//@EnableJpaRepositories("parqueadero.repositorio")
//@EnableJpaRepositories(basePackages = {"parqueadero.*","parqueadero.repositorio"})
//@ComponentScan(basePackages = {"parqueadero.*","parqueadero.repositorio"})
@ComponentScan(basePackages = {"parqueadero.*","parqueadero.repositorio"})
public class ParqueaderoApplication {

    @Autowired
    private IAdministradorParqueaderoServicio administradorParqueaderoServicio;
    
    @PostConstruct
    public void setup() { 
        init();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ParqueaderoApplication.class, args);
        
    }
    
    private  void init() {
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
            tipoVehiculo = new TipoVehiculo(enumTipoVehiculo, enumTipoVehiculo.name());
            administradorParqueaderoServicio.registrarTipoVehiculo(tipoVehiculo);
        }
        
    }

    private void crearEstadoParqueo() {
        EstadoParqueo estadoParqueo;
        for (EnumEstadoParqueo enumEstadoParqueo: EnumEstadoParqueo.values()) {
            estadoParqueo = new EstadoParqueo(enumEstadoParqueo, enumEstadoParqueo.name());

            administradorParqueaderoServicio.registrarEstadoParqueo(estadoParqueo);
        }
        
    }
}
