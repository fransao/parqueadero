package parqueadero.ws.servicecontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.dominio.Vehiculo;
import parqueadero.servicio.IGestionVehiculoServicio;

@RestController
public class VehiculoController {

    @Autowired
    IGestionVehiculoServicio gestionVehiculoServicio;
    
    @RequestMapping(value="/vehiculo", method=RequestMethod.GET)
    public List<Vehiculo> consultarVehiculosEnParqueadero() {
        
        List<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
        
        
        return listVehiculos;
    }
    
}
