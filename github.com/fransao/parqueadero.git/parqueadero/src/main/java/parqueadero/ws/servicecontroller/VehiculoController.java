package parqueadero.ws.servicecontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.servicio.IParqueaderoServicio;

@RestController
public class VehiculoController {

    @Autowired
    IParqueaderoServicio parqueaderoservicio;
    
    @RequestMapping(value="/vehiculo", method=RequestMethod.GET)
    public List<Vehiculo> consultarVehiculosEnParqueadero() {
        
        List<GestionVehiculo> listGestionVehiculo = parqueaderoservicio.obtenerVehiculosEnElParqueadero();
        
        return listGestionVehiculo.stream().map(g -> g.getVehiculo()).filter(r -> r != null).collect(Collectors.toList());
    }
    
}
