package parqueadero.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.repositorio.IRepositorioEstadoParqueo;
import parqueadero.repositorio.IRepositorioGestionParqueadero;
import parqueadero.repositorio.IRepositorioVehiculo;

@Service
public class VigilanteServicio implements IVigilanteServicio {

    @Autowired
    public IRepositorioGestionParqueadero repositorioGestionParqueadero;
    
    @Autowired
    IRepositorioEstadoParqueo repositorioEstadoParqueo;
    
    @Autowired
    IRepositorioVehiculo repositorioVehiculo;
    
    @Override
    public void registrarIngresoVehiculo(GestionVehiculo gestionVehiculo) {
        repositorioGestionParqueadero.save(VehiculoBuilder.convertirGestionVehiculoAEntidad(gestionVehiculo));
        
    }

    @Override
    public GestionVehiculo estaVehiculoIngresado(Vehiculo vehiculo) {
        GestionVehiculoEntidad optGestionVehiculo = repositorioGestionParqueadero.estaVehiculoIngresado(vehiculo.getPlaca(), EnumEstadoParqueo.INGRESADO.getEstadoParqueo());
        return VehiculoBuilder.convertirGestionVehiculoADominio(optGestionVehiculo);
    }

    @Override
    public void registrarSalidaVehiculo(GestionVehiculo gestionVehiculo) {
        repositorioGestionParqueadero.save(VehiculoBuilder.convertirGestionVehiculoAEntidad(gestionVehiculo));
    }

    public void registrarPlacaVehiculo(Vehiculo vehiculo) {
        repositorioVehiculo.save(VehiculoBuilder.convertirVehiculoAEntidad(vehiculo));
    }

}
