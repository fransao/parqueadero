package parqueadero.servicio;

import java.util.List;

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
import parqueadero.util.Util;

@Service
public class VigilanteServicio implements IVigilanteServicio {

    @Autowired
    public IRepositorioGestionParqueadero repositorioGestionParqueadero;
    
    @Autowired
    IRepositorioEstadoParqueo repositorioEstadoParqueo;
    
    @Autowired
    IRepositorioVehiculo repositorioVehiculo;
    
    @Override
    public GestionVehiculo registrarIngresoVehiculo(GestionVehiculo gestionVehiculo) {
        return VehiculoBuilder.convertirGestionVehiculoADominio(repositorioGestionParqueadero.save(VehiculoBuilder.convertirGestionVehiculoAEntidad(gestionVehiculo)));
    }

    @Override
    public GestionVehiculo estaVehiculoIngresado(Vehiculo vehiculo) {
        List<GestionVehiculoEntidad> gestionVehiculo = repositorioGestionParqueadero.estaVehiculoIngresado(vehiculo.getPlaca(), EnumEstadoParqueo.INGRESADO.getEstadoParqueo());
        if (!Util.isEmpty(gestionVehiculo)) {
            return VehiculoBuilder.convertirGestionVehiculoADominio(gestionVehiculo.get(0));
        }
        return null;
    }

    @Override
    public GestionVehiculo registrarSalidaVehiculo(GestionVehiculo gestionVehiculo) {
        return VehiculoBuilder.convertirGestionVehiculoADominio(repositorioGestionParqueadero.save(VehiculoBuilder.convertirGestionVehiculoAEntidad(gestionVehiculo)));
    }

    public void registrarPlacaVehiculo(Vehiculo vehiculo) {
        repositorioVehiculo.save(VehiculoBuilder.convertirVehiculoAEntidad(vehiculo));
    }

    @Override
    public void desocuparParqueadero() {
        repositorioGestionParqueadero.deleteAll();
    }

}
