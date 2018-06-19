package parqueadero.servicio;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import parqueadero.dominio.Moto;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.persistencia.entidad.RecargoCilindrajeEntidad;
import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidad;
import parqueadero.repositorio.IRepositorioRecargo;
import parqueadero.repositorio.IRepositorioTarifa;

public class ParqueaderoServicio implements IParqueaderoServicio {

    @Autowired
    IRepositorioRecargo repositorioRecargo;
    
    @Autowired
    IRepositorioTarifa repositorioTarifa;
    
    @Override
    public List<TarifaXTipoVehiculo> obtenerTarifasXTipoVehiculo() {
        
        List<TarifaXTipoVehiculoEntidad> listTarifasTipoVehiculo = StreamSupport.stream(repositorioTarifa.findAll().spliterator(), false).collect(Collectors.toList());
        
        return listTarifasTipoVehiculo.stream().map(VehiculoBuilder::convertirTarifaXTipoVehiculoADomino).filter(t -> t != null).collect(Collectors.toList());
    }

    @Override
    public List<RecargoCilindraje> obtenerRecargos() {
        List<RecargoCilindrajeEntidad> listRecargo = StreamSupport.stream(repositorioRecargo.findAll().spliterator(), false).collect(Collectors.toList());
        
        return listRecargo.stream().map(VehiculoBuilder::convertirRecargoEntidadADominio).filter(r -> r != null).collect(Collectors.toList());
    }

    @Override
    public RecargoCilindraje obtenerRecargo(Vehiculo vehiculo) {
        if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            return VehiculoBuilder.convertirRecargoEntidadADominio(repositorioRecargo.obtenerRecargoVehiculo(moto.getTipoVehiculo().getTipoVehiculo(), moto.getCilindraje()));
        }
        return null;
    }

}
