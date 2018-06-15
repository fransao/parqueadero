package parqueadero.persistencia.builder;

import parqueadero.dominio.Carro;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Moto;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.persistencia.entidad.EstadoParqueoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidadPK;
import parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import parqueadero.persistencia.entidad.VehiculoEntidad;

public class VehiculoBuilder {

    private VehiculoBuilder () {
        
    }
    
    public static Vehiculo convertirVehiculoADominio (VehiculoEntidad vehiculoEntidad) {
        Vehiculo vehiculo = null;
        if (vehiculoEntidad != null) {
            return new Vehiculo (vehiculoEntidad.getPlaca(), tipoVehiculoAEnumerado(vehiculoEntidad.getTipoVehiculo().getTipoVehiculo()));
        }
        return vehiculo;
    }
    
    private static EnumTipoVehiculo tipoVehiculoAEnumerado(Integer tipoVehiculo) {
        return EnumTipoVehiculo.getEnumTipoVehiculo(tipoVehiculo);
    }

    public static VehiculoEntidad convertirVehiculoAEntidad (Vehiculo vehiculo) {
        
        VehiculoEntidad vehiculoEntidad = new VehiculoEntidad();
        vehiculoEntidad.setPlaca(vehiculo.getPlaca());
        vehiculoEntidad.setTipoVehiculo(getTipoVehiculo(vehiculo));
        vehiculoEntidad.setCilindraje(convertirVehiculoAMoto(vehiculo).getCilindraje());
        
        return vehiculoEntidad;
    }
    
    private static Moto convertirVehiculoAMoto (Vehiculo vehiculo) {
        Moto moto = null;
        if (vehiculo instanceof Moto) {
            moto = (Moto) vehiculo;
        }
        return moto;
    }
    
    private static Carro convertirVehiculoACarro (Vehiculo vehiculo) {
        Carro carro = null;
        if (vehiculo instanceof Carro) {
            carro = (Carro) vehiculo;
        }
        return carro;
    }
    
    private static TipoVehiculoEntidad getTipoVehiculo (Vehiculo vehiculo) {
        TipoVehiculoEntidad tipoVehiculoEntidad = new TipoVehiculoEntidad();
        
        if (vehiculo instanceof Carro) {
            tipoVehiculoEntidad.setTipoVehiculo(EnumTipoVehiculo.CARRO.getTipoVehiculo());
        } else if (vehiculo instanceof Moto) {
            tipoVehiculoEntidad.setTipoVehiculo(EnumTipoVehiculo.MOTO.getTipoVehiculo());
        }
        
        return tipoVehiculoEntidad;
    }
    
    public static TipoVehiculo convertirTipoVehiculoADominio (TipoVehiculoEntidad tipoVehiculoEntidad) {
        TipoVehiculo tipoVehiculo = null;
        if (tipoVehiculoEntidad != null) {
            tipoVehiculo = new TipoVehiculo(tipoVehiculoEntidad.getTipoVehiculo(), tipoVehiculoEntidad.getNombreTipoVehiculo());
        }
        return tipoVehiculo;
    }
    
    public static TipoVehiculoEntidad convertirTipoVehiculoAEntidad (TipoVehiculo tipoVehiculo) {
        TipoVehiculoEntidad tipoVehiculoEntidad = new TipoVehiculoEntidad();
        tipoVehiculoEntidad.setTipoVehiculo(tipoVehiculo.getCodTipoVehiculo());
        tipoVehiculoEntidad.setNombreTipoVehiculo(tipoVehiculo.getNombreTipoVehiculo());
        
        return tipoVehiculoEntidad;
    }
    
    public static GestionVehiculoEntidad convertirGestionVehiculoAEntidad (GestionVehiculo gestionVehiculo) {
        GestionVehiculoEntidad gestionVehiculoEntidad = new GestionVehiculoEntidad();
        gestionVehiculoEntidad.setGestionVehiculoEntidadPK(convertirGestionVehiculoAEntidadPK(gestionVehiculo));
        gestionVehiculoEntidad.setEstadoParqueo(convertirEstadoParqueoAEstadoParqueoEntidad(gestionVehiculo));
        gestionVehiculoEntidad.setFechaSalida(gestionVehiculo.getFechaSalida());
        gestionVehiculoEntidad.setValor(gestionVehiculo.getValor());
        
        return gestionVehiculoEntidad;
    }

    private static EstadoParqueoEntidad convertirEstadoParqueoAEstadoParqueoEntidad(GestionVehiculo gestionVehiculo) {
        EstadoParqueoEntidad estadoParqueoEntidad = new EstadoParqueoEntidad();
        estadoParqueoEntidad.setEstado(gestionVehiculo.getEstadoParqueo());
        
        return estadoParqueoEntidad;
    }

    private static GestionVehiculoEntidadPK convertirGestionVehiculoAEntidadPK(GestionVehiculo gestionVehiculo) {
        GestionVehiculoEntidadPK gestionVehiculoEntidadPK = new GestionVehiculoEntidadPK();
        gestionVehiculoEntidadPK.setTipoVehiculo(gestionVehiculo.getTipoVehiculo().getCodTipoVehiculo());
        gestionVehiculoEntidadPK.setPlaca(gestionVehiculo.getVehiculo().getPlaca());
        gestionVehiculoEntidadPK.setFechaIngreso(gestionVehiculo.getFechaIngreso());
        
        return gestionVehiculoEntidadPK;
    }
}
