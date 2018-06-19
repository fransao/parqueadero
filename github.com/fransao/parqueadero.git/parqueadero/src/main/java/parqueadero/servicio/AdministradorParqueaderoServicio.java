package parqueadero.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Moto;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.persistencia.entidad.EstadoParqueoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.persistencia.entidad.RecargoCilindrajeEntidad;
import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidad;
import parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import parqueadero.persistencia.entidad.VehiculoEntidad;
import parqueadero.repositorio.IRepositorioEstadoParqueo;
import parqueadero.repositorio.IRepositorioGestionParqueadero;
import parqueadero.repositorio.IRepositorioRecargo;
import parqueadero.repositorio.IRepositorioTarifa;
import parqueadero.repositorio.IRepositorioTipoVehiculo;
import parqueadero.repositorio.IRepositorioVehiculo;

@Service
public class AdministradorParqueaderoServicio implements IAdministradorParqueaderoServicio {

    @Autowired
    IRepositorioRecargo repositorioRecargo;
    
    @Autowired
    IRepositorioTarifa repositorioTarifa;
    
    @Autowired
    IRepositorioGestionParqueadero repositorioGestionParqueadero;
    
    @Autowired
    IRepositorioTipoVehiculo repositorioTipoVehiculo;
    
    @Autowired
    IRepositorioEstadoParqueo repositorioEstadoParqueo;
    
    @Autowired
    IRepositorioVehiculo repositorioVehiculo;
    
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
    
    @Override
    public Vehiculo obtenerVehiculoPorPlaca(String placa) {
        Optional<VehiculoEntidad> vehiculo = repositorioVehiculo.findById(placa);
        if (vehiculo.isPresent()) {
            return VehiculoBuilder.convertirVehiculoADominio(vehiculo.get());
        }

        return null;
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

    @Override
    public void registrarRecargo(RecargoCilindraje recargoCilindraje) {
        repositorioRecargo.save(VehiculoBuilder.convertirRecargoAEntidad(recargoCilindraje));
    }

    @Override
    public void registrarTarifas(List<TarifaXTipoVehiculo> listTarifaVehiculo) {
        List<TarifaXTipoVehiculoEntidad> listTarifaEntidad = listTarifaVehiculo.stream().map(VehiculoBuilder::convertirTarifaXTipoVehiculoAEntidad).filter(r -> r != null).collect(Collectors.toList());
        for (TarifaXTipoVehiculoEntidad tarifaEntidad: listTarifaEntidad) {
            repositorioTarifa.save(tarifaEntidad);
        }
    }

    @Override
    public List<GestionVehiculo> obtenerVehiculosEnElParqueadero() {
        List<GestionVehiculoEntidad> vehiculosEnParqueadero = StreamSupport.stream(repositorioGestionParqueadero.findAll().spliterator(), false).collect(Collectors.toList());
        return vehiculosEnParqueadero.stream().map(VehiculoBuilder::convertirGestionVehiculoADominio).filter(r -> r != null).collect(Collectors.toList());
    }

}
