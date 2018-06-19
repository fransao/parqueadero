package parqueadero.ws.servicecontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.servicio.IAdministradorParqueaderoServicio;
import parqueadero.servicio.IVigilanteServicio;
import testdatabuilder.VehiculoTestDataBuilder;

@RestController
@RequestMapping("/parqueadero")
@EnableAutoConfiguration
public class VehiculoController {

    @Autowired
    IAdministradorParqueaderoServicio parqueaderoservicio;
    
    @Autowired
    IVigilanteServicio vigilanteServicio;
    
    @RequestMapping(value="/vehiculo/", method=RequestMethod.GET)
    public List<GestionVehiculo> consultarVehiculosEnParqueadero() {
        return parqueaderoservicio.obtenerVehiculosEnElParqueadero();
    }
    
    @RequestMapping(value="/vehiculo{placa}", method=RequestMethod.GET)
    public GestionVehiculo consultarVehiculoEnParqueadero(@PathVariable("placa") String placa) {
        return vigilanteServicio.estaVehiculoIngresado(new VehiculoTestDataBuilder().conPlaca(placa).build());
    }
    
    @RequestMapping(value = "/vehiculo/", method = RequestMethod.POST)
    public GestionVehiculo registrarIngresoVehiculo(@RequestBody Vehiculo vehiculo, UriComponentsBuilder ucBuilder) {
 
        if (vigilanteServicio.estaVehiculoIngresado(vehiculo) == null) {
            vigilanteServicio.registrarPlacaVehiculo(vehiculo);
        }
        
        GestionVehiculo gestionVehiculo = new GestionVehiculo(vehiculo, new Date());
        
        vigilanteServicio.registrarIngresoVehiculo(gestionVehiculo);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/parqueadero/vehiculo/{placa}").buildAndExpand(vehiculo.getPlaca()).toUri());
        return gestionVehiculo;
    }
    
    @RequestMapping(value = "/vehiculo/{placa}", method = RequestMethod.PUT)
    public GestionVehiculo registrarSalidaVehiculo(@RequestBody Vehiculo vehiculo, UriComponentsBuilder ucBuilder) {
        
        GestionVehiculo gestionVehiculo = vigilanteServicio.estaVehiculoIngresado(vehiculo);
        gestionVehiculo.setEstadoParqueo(EnumEstadoParqueo.SALIDA);
        gestionVehiculo.setFechaSalida(new Date());
        gestionVehiculo.setValor(0.0f);
        
        vigilanteServicio.registrarSalidaVehiculo(gestionVehiculo);
        return gestionVehiculo;
    }
    
}
