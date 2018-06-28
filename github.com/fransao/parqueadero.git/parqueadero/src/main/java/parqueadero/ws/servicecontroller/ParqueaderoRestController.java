package parqueadero.ws.servicecontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.RequestVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.dominio.Vigilante;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.servicio.IAdministradorParqueaderoServicio;
import parqueadero.servicio.IVigilanteServicio;

@RestController
@RequestMapping("/parqueadero")
@EnableAutoConfiguration
public class ParqueaderoRestController {

    @Autowired
    IAdministradorParqueaderoServicio administradorParqueaderoServicio;
    
    @Autowired
    IVigilanteServicio vigilanteServicio;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/vehiculo/", method=RequestMethod.GET)
    public List<GestionVehiculo> consultarVehiculosEnParqueadero() {
        return administradorParqueaderoServicio.obtenerVehiculosEnElParqueadero();
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping
    @RequestMapping(value="/vehiculo/{placa}")
    @ResponseBody
    public GestionVehiculo consultarVehiculoEnParqueadero(@PathVariable("placa") String placa) {
        return vigilanteServicio.estaVehiculoIngresado(new Vehiculo(placa));
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping
    @RequestMapping(value = "/vehiculo/")
    public GestionVehiculo registrarIngresoVehiculo(@RequestBody RequestVehiculo requestVehiculo) {
 
        Vehiculo vehiculo; 
        if (EnumTipoVehiculo.MOTO.equals(requestVehiculo.getTipoVehiculo())) {
            vehiculo = requestVehiculo.getMoto();
        } else if (EnumTipoVehiculo.CARRO.equals(requestVehiculo.getTipoVehiculo())) {
            vehiculo = requestVehiculo.getCarro();
        } else {
            vehiculo = requestVehiculo.getVehiculo(); 
        }
        
        if (administradorParqueaderoServicio.obtenerVehiculoPorPlaca(vehiculo.getPlaca()) == null) {
            vigilanteServicio.registrarPlacaVehiculo(vehiculo);
        }
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        GestionVehiculo obtenerVehiculoIngresado = vigilante.obtenerVehiculoIngresado(vehiculo);
        if (obtenerVehiculoIngresado == null) {
            vigilante.registrarIngresoVehiculoAParqueadero(vehiculo, new Date());
        }
        
        return vigilante.obtenerVehiculoIngresado(vehiculo);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/vehiculo/{placa}", method = RequestMethod.PUT)
    public GestionVehiculo calcularCobroParqueadero(@PathVariable String placa) {
        
        Vigilante vigilante = new Vigilante (vigilanteServicio, administradorParqueaderoServicio);
        
        GestionVehiculo gestionVehiculo = vigilanteServicio.estaVehiculoIngresado(new Vehiculo(placa));
        if (gestionVehiculo != null) {
            gestionVehiculo.setEstadoParqueo(EnumEstadoParqueo.SALIDA);
            gestionVehiculo.setFechaSalida(new Date());
            gestionVehiculo.setValor(vigilante.generarCobroVechiculoParqueo(gestionVehiculo));
            
            vigilanteServicio.registrarSalidaVehiculo(gestionVehiculo);
        }
        
        return gestionVehiculo;
    }
    
}
