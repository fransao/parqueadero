package parqueadero.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.repositorio.IRepositorioTipoVehiculo;
import parqueadero.repositorio.IRepositorioVehiculo;

@Service
public class VehiculoServicio implements IVehiculoServicio {

    @Autowired 
    IRepositorioVehiculo repositorioVehiculo;
    
    @Autowired
    IRepositorioTipoVehiculo repositorioTipoVehiculo;
    
    public void registrarPlacaVehiculo(Vehiculo vehiculo) {
        repositorioVehiculo.save(VehiculoBuilder.convertirVehiculoAEntidad(vehiculo));
    }

    @Override
    public Vehiculo obtenerVehiculoPorPlaca(String placa) {
        return VehiculoBuilder.convertirVehiculoADominio(repositorioVehiculo.findById(placa).get());
    }

    @Override
    public void registrarTipoVehiculo(TipoVehiculo tipoVehiculo) {
        repositorioTipoVehiculo.save(VehiculoBuilder.convertirTipoVehiculoAEntidad(tipoVehiculo));
        
    }

    @Override
    public TipoVehiculo obtenerTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
        return VehiculoBuilder.convertirTipoVehiculoADominio(repositorioTipoVehiculo.findById(tipoVehiculo.getTipoVehiculo()).get());
    }
    
}
