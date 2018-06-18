package parqueadero.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import parqueadero.persistencia.entidad.VehiculoEntidad;
import parqueadero.repositorio.IRepositorioEstadoParqueo;
import parqueadero.repositorio.IRepositorioTipoVehiculo;
import parqueadero.repositorio.IRepositorioVehiculo;

@Service
public class VehiculoServicio implements IVehiculoServicio {

    @Autowired 
    IRepositorioVehiculo repositorioVehiculo;
    
    @Autowired
    IRepositorioTipoVehiculo repositorioTipoVehiculo;
    
    @Autowired
    IRepositorioEstadoParqueo repositorioEstadoParqueo;
    
    public void registrarPlacaVehiculo(Vehiculo vehiculo) {
        repositorioVehiculo.save(VehiculoBuilder.convertirVehiculoAEntidad(vehiculo));
    }

    @Override
    public Vehiculo obtenerVehiculoPorPlaca(String placa) {
        Optional<VehiculoEntidad> vehiculo = repositorioVehiculo.findById(placa);
        if (vehiculo.isPresent()) {
            return VehiculoBuilder.convertirVehiculoADominio(vehiculo.get());
        }

        return null;
    }

    @Override
    public void registrarTipoVehiculo(TipoVehiculo tipoVehiculo) {
        repositorioTipoVehiculo.save(VehiculoBuilder.convertirTipoVehiculoAEntidad(tipoVehiculo));
        
    }

    @Override
    public TipoVehiculo obtenerTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
        Optional<TipoVehiculoEntidad> optTipoVehiculo = repositorioTipoVehiculo.findById(tipoVehiculo.getTipoVehiculo());
        if (optTipoVehiculo.isPresent()) {
            return VehiculoBuilder.convertirTipoVehiculoADominio(optTipoVehiculo.get());
        }
        return null;
    }

    @Override
    public void registrarEstadoParqueo(EstadoParqueo estadoParqueo) {
        repositorioEstadoParqueo.save(VehiculoBuilder.convertirEstadoParqueoAEntidad(estadoParqueo));
    }
    
}
