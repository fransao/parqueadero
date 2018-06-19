package parqueadero.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.persistencia.entidad.EstadoParqueoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.repositorio.IRepositorioEstadoParqueo;
import parqueadero.repositorio.IRepositorioGestionParqueadero;

@Service
public class GestionVehiculoServicio implements IGestionVehiculoServicio {

    @Autowired
    public IRepositorioGestionParqueadero repositorioGestionParqueadero;
    
    @Autowired
    IRepositorioEstadoParqueo repositorioEstadoParqueo;
    
    @Override
    public void registrarIngresoVehiculo(GestionVehiculo gestionVehiculo) {
        repositorioGestionParqueadero.save(VehiculoBuilder.convertirGestionVehiculoAEntidad(gestionVehiculo));
        
    }

    @Override
    public GestionVehiculoEntidad estaVehiculoIngresado(Vehiculo vehiculo) {
        return repositorioGestionParqueadero.estaVehiculoIngresado(vehiculo.getPlaca(), EnumEstadoParqueo.INGRESADO.getEstadoParqueo());
    }

    @Override
    public EstadoParqueo obtenerEstadoParqueo(EstadoParqueo estadoParqueo) {
         Optional<EstadoParqueoEntidad> optEstadoParqueo = repositorioEstadoParqueo.findById(estadoParqueo.getEstadoParqueo().getEstadoParqueo());
         if (optEstadoParqueo.isPresent()) {
             return VehiculoBuilder.convertirEstadoParqueoADominio(optEstadoParqueo.get());
         }
         return null;
    }

    @Override
    public void registrarSalidaVehiculo(GestionVehiculo gestionVehiculo) {
        repositorioGestionParqueadero.save(VehiculoBuilder.convertirGestionVehiculoAEntidad(gestionVehiculo));
    }

}
