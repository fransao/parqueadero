package parqueadero.servicio;

import org.springframework.beans.factory.annotation.Autowired;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.persistencia.entidad.GestionVehiculoEntidadPK;
import parqueadero.repositorio.IRepositorioGestionParqueadero;

public class GestionVehiculoServicio implements IGestionVehiculoServicio {

    @Autowired
    public IRepositorioGestionParqueadero repositorioGestionParqueadero;
    
    @Override
    public void ingresarVehiculo(GestionVehiculo gestionVehiculo) {
        repositorioGestionParqueadero.save(VehiculoBuilder.convertirGestionVehiculoAEntidad(gestionVehiculo));
        
    }

    @Override
    public Vehiculo estaVehiculoIngresado(Vehiculo vehiculo) {
        
        
        
        EnumTipoVehiculo tipoVehiculo = vehiculo.getTipoVehiculo();
        
        GestionVehiculoEntidadPK gestionVehiculoEntidadPK = new GestionVehiculoEntidadPK();
        gestionVehiculoEntidadPK.setPlaca(vehiculo.getPlaca());
        gestionVehiculoEntidadPK.setTipoVehiculo(vehiculo.getTipoVehiculo().getTipoVehiculo());
//        gestionVehiculoEntidadPK.setFechaIngreso();
        
        return (Vehiculo) null; //repositorioGestionParqueadero.findById(id);
    }


}
