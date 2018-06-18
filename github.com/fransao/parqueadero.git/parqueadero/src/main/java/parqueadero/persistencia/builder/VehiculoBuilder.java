package parqueadero.persistencia.builder;

import parqueadero.dominio.Carro;
import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Moto;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.persistencia.entidad.EstadoParqueoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidadPK;
import parqueadero.persistencia.entidad.RecargoCilindrajeEntidad;
import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidad;
import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidadPK;
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
            tipoVehiculo = new TipoVehiculo(EnumTipoVehiculo.getEnumTipoVehiculo(tipoVehiculoEntidad.getTipoVehiculo()), tipoVehiculoEntidad.getNombreTipoVehiculo());
        }
        return tipoVehiculo;
    }
    
    public static TipoVehiculoEntidad convertirTipoVehiculoAEntidad (TipoVehiculo tipoVehiculo) {
        TipoVehiculoEntidad tipoVehiculoEntidad = new TipoVehiculoEntidad();
        tipoVehiculoEntidad.setTipoVehiculo(tipoVehiculo.getCodTipoVehiculo().getTipoVehiculo());
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

    public static GestionVehiculo convertirGestionVehiculoADominio (GestionVehiculoEntidad gestionVehiculoEntidad) {
        GestionVehiculo gestionVehiculo = new GestionVehiculo();
        gestionVehiculo.setEnumTipoVehiculo(EnumTipoVehiculo.getEnumTipoVehiculo(gestionVehiculoEntidad.getGestionVehiculoEntidadPK().getTipoVehiculo()));
        gestionVehiculo.setEstadoParqueo(EnumEstadoParqueo.getEnumEstadoParqueo(gestionVehiculoEntidad.getEstadoParqueo().getEstado()));
        gestionVehiculo.setFechaIngreso(gestionVehiculoEntidad.getGestionVehiculoEntidadPK().getFechaIngreso());
        gestionVehiculo.setValor(gestionVehiculoEntidad.getValor());
        
        return gestionVehiculo;
    }
    
    private static EstadoParqueoEntidad convertirEstadoParqueoAEstadoParqueoEntidad(GestionVehiculo gestionVehiculo) {
        EstadoParqueoEntidad estadoParqueoEntidad = new EstadoParqueoEntidad();
        estadoParqueoEntidad.setEstado(gestionVehiculo.getEstadoParqueo().getEstadoParqueo());
        
        return estadoParqueoEntidad;
    }

    private static GestionVehiculoEntidadPK convertirGestionVehiculoAEntidadPK(GestionVehiculo gestionVehiculo) {
        GestionVehiculoEntidadPK gestionVehiculoEntidadPK = new GestionVehiculoEntidadPK();
        gestionVehiculoEntidadPK.setTipoVehiculo(gestionVehiculo.getEnumTipoVehiculo().getTipoVehiculo());
        gestionVehiculoEntidadPK.setPlaca(gestionVehiculo.getVehiculo().getPlaca());
        gestionVehiculoEntidadPK.setFechaIngreso(gestionVehiculo.getFechaIngreso());
        
        return gestionVehiculoEntidadPK;
    }

    public static EstadoParqueoEntidad convertirEstadoParqueoAEntidad(EstadoParqueo estadoParqueo) {
        EstadoParqueoEntidad estadoParqueoEntidad = new EstadoParqueoEntidad();
        estadoParqueoEntidad.setEstado(estadoParqueo.getEstadoParqueo().getEstadoParqueo());
        estadoParqueoEntidad.setNombreEstado(estadoParqueo.getNombreEstadoParqueo());
        
        return estadoParqueoEntidad;
    }

    public static EstadoParqueo convertirEstadoParqueoADominio(EstadoParqueoEntidad estadoParqueoEntidad) {
        EstadoParqueo estadoParqueo = new EstadoParqueo();
        estadoParqueo.setEstadoParqueo(EnumEstadoParqueo.getEnumEstadoParqueo(estadoParqueoEntidad.getEstado()));
        estadoParqueo.setNombreEstadoParqueo(estadoParqueoEntidad.getNombreEstado());
        
        return estadoParqueo;
    }

    public static TarifaXTipoVehiculo convertirTarifaXTipoVehiculoADomino (TarifaXTipoVehiculoEntidad tarifaEntidad) {
        TarifaXTipoVehiculo tarifaXTipoVehiculo = new TarifaXTipoVehiculo();
        tarifaXTipoVehiculo.setTipoVehiculo(EnumTipoVehiculo.getEnumTipoVehiculo(tarifaEntidad.getTarifaXTipoVehiculoPK().getTipoVehiculo()));
        tarifaXTipoVehiculo.setUnidadTiempo(EnumTiempo.getEnum(tarifaEntidad.getTarifaXTipoVehiculoPK().getUnidadTiempo()));
        tarifaXTipoVehiculo.setValor(tarifaEntidad.getValor());
        
        return tarifaXTipoVehiculo;
    }
    
    public static TarifaXTipoVehiculoEntidad convertirTarifaXTipoVehiculoAEntidad (TarifaXTipoVehiculo tarifaXTipoVehiculo) {
        TarifaXTipoVehiculoEntidad tarifaXTipoVehiculoEntidad = new TarifaXTipoVehiculoEntidad();
        tarifaXTipoVehiculoEntidad.setTarifaXTipoVehiculoPK(convertirTarifaTipoVehiculoAEntidadPK(tarifaXTipoVehiculo));
        
        return tarifaXTipoVehiculoEntidad;
    }

    private static TarifaXTipoVehiculoEntidadPK convertirTarifaTipoVehiculoAEntidadPK(TarifaXTipoVehiculo tarifaXTipoVehiculo) {
        TarifaXTipoVehiculoEntidadPK tarifaXTipoVehiculoEntidadPK = new TarifaXTipoVehiculoEntidadPK();
        tarifaXTipoVehiculoEntidadPK.setTipoVehiculo(tarifaXTipoVehiculo.getTipoVehiculo().getTipoVehiculo());
        tarifaXTipoVehiculoEntidadPK.setUnidadTiempoEntidad(tarifaXTipoVehiculo.getUnidadTiempo().getTiempo());
        
        return tarifaXTipoVehiculoEntidadPK;
    }
    
    public static RecargoCilindraje convertirRecargoEntidadADominio (RecargoCilindrajeEntidad recargoEntidad) {
        RecargoCilindraje recargo = new RecargoCilindraje();
        recargo.setIdCilindraje(recargoEntidad.getIdCilindraje());
        recargo.setCilindrajeDesde(recargoEntidad.getCilindrajeDesde());
        recargo.setCilindrajeHasta(recargoEntidad.getCilindrajeHasta());
        recargo.setValor(recargoEntidad.getValor());
        
        return recargo;
    }
    
    public static RecargoCilindrajeEntidad convertirRecargoAEntidad (RecargoCilindraje recargo) {
        RecargoCilindrajeEntidad recargoEntidad = new RecargoCilindrajeEntidad();
        recargoEntidad.setIdCilindraje(recargo.getIdCilindraje());
        recargoEntidad.setCilindrajeDesde(recargo.getCilindrajeDesde());
        recargoEntidad.setCilindrajeHasta(recargo.getCilindrajeHasta());
        recargoEntidad.setValor(recargo.getValor());
        
        return recargoEntidad;
    }
    
}
